package Homework.week12;

import java.io.*;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestProcessor implements Runnable {

    private final static Logger logger = Logger.getLogger(RequestProcessor.class.getCanonicalName());

    private File rootDirectory;
    private String indexFileName = "logo.png";
    private Socket connection;

    public RequestProcessor(File rootDirectory, String indexFileName, Socket connection) {
        if (rootDirectory.isFile()) {
            throw new IllegalArgumentException("rootDirectory must be a directory, not a file");
        }

        try {
            rootDirectory = rootDirectory.getCanonicalFile(); // 절대경로
        } catch (IOException ignored) {
        }
        this.rootDirectory = rootDirectory;

        if (indexFileName != null) this.indexFileName = indexFileName;
        this.connection = connection;
    }

    @Override
    public void run() {
        // for security checks
        String root = rootDirectory.getPath();

        try {
            OutputStream raw = new BufferedOutputStream(connection.getOutputStream());
            Writer out = new OutputStreamWriter(raw);
            Reader in = new InputStreamReader(new BufferedInputStream(connection.getInputStream()), "US-ASCII");
            StringBuilder requestLine = new StringBuilder();

            while (true) {
                int c = in.read();
                if (c == '\r' || c == '\n') break;
                requestLine.append((char) c);
            }

            String get = requestLine.toString();

            logger.info(connection.getRemoteSocketAddress() + " " + get);

            String[] tokens = get.split("\\s+");
            String method = tokens[0];
            String version = "";

            if (method.equals("GET")) {
                String fileName = tokens[1];
                if (fileName.endsWith("/")) fileName += indexFileName;
                String contentType = URLConnection.getFileNameMap().getContentTypeFor(fileName);
                if (tokens.length > 2) {
                    version = tokens[2];
                }

                File theFile = new File(rootDirectory, fileName.substring(1, fileName.length()));
                if (theFile.canRead()
                        // Don't let clients outside the document root
                        && theFile.getCanonicalPath().startsWith(root)) {
                    byte[] theData = Files.readAllBytes(theFile.toPath());
                    if (version.startsWith("HTTP/")) { // send a MIME header
                        sendHeader(out, "HTTP/1.0 200 OK", contentType, theData.length);
                    }

                    // send the file; it may be an image or other binary data
                    // so use the underlying output stream
                    // instead of the writer
                    raw.write(theData);
                    raw.flush();

                } else { // can't find the file
                    String body = "<HTML>\r\n" +
                            "<HEAD><TITLE>File Not Found</TITLE></HEAD>\r\n" +
                            "<BODY><H1>HTTP Error 404: File Not Found</H1></BODY>\n" +
                            "</HTML>\r\n";

                    if (version.startsWith("HTTP/")) { // send a MIME header
                        sendHeader(out, "HTTP/1.0 404 File Not Found", "text/html; charset=utf-8", body.length());
                    }
                    out.write(body);
                    out.flush();
                }
            }

            // ------------------------------------------------------------------------------------

            // TODO: Modify JHTTP.java/RequestProcessor.java to handle DELETE method
            else if (method.equals("DELETE")) {
                String fileName = tokens[1];
                if (fileName.endsWith("/")) fileName += indexFileName;
                if (tokens.length > 2) {
                    version = tokens[2];
                }

                File theFile = new File(rootDirectory, fileName.substring(1, fileName.length()));

                // TODO: file found (200)
                if (theFile.canRead() && theFile.getCanonicalPath().startsWith(root)) {
                    StringBuilder body = new StringBuilder();
                    try {
                        if (theFile.delete()) {
                            body.append("<HTML>\r\n" +
                                    "<HEAD><TITLE>HTTP/1.0 200 OK</TITLE></HEAD>\r\n" +
                                    "<BODY><H1>Success</H1></BODY>\n" +
                                    "</HTML>\r\n");
                        } else {
                            throw new IOException();
                        }
                    } catch (IOException exception) {
                        body.append("<HTML>\r\n" +
                                "<HEAD><TITLE>HTTP/1.0 200 OK</TITLE></HEAD>\r\n" +
                                "<BODY><H1>Failure</H1></BODY>\n" +
                                "</HTML>\r\n");
                    }
                    if (version.startsWith("HTTP/")) {
                        sendHeader(out, "HTTP/1.0 200 OK", "text/html; charset=utf-8", body.toString().length());
                    }
                    out.write(body.toString());
                    out.flush();
                }

                // TODO: file not found (404)
                else { // can't find the file
                    logger.log(Level.SEVERE, "파일 없음");

                    String body = "<HTML>\r\n" +
                            "<HEAD><TITLE>File Not Found</TITLE></HEAD>\r\n" +
                            "<BODY><H1>HTTP Error 404: File Not Found</H1></BODY>\n" +
                            "</HTML>\r\n";

                    if (version.startsWith("HTTP/")) { // send a MIME header
                        sendHeader(out, "HTTP/1.0 404 File Not Found", "text/html; charset=utf-8", body.length());
                    }
                    out.write(body);
                    out.flush();
                }

                // ------------------------------------------------------------------------------------

            } else {
                String body = "<HTML>\r\n" +
                        "<HEAD><TITLE>Not Implemented</TITLE>\r\n" +
                        "</HEAD>\r\n" +
                        "<BODY>" +
                        "<H1>HTTP Error 501: Not Implemented</H1>\r\n" +
                        "</BODY></HTML>\r\n";

                if (version.startsWith("HTTP/")) { // send a MIME header
                    sendHeader(out, "HTTP/1.0 501 Not Implemented", "text/html; charset=utf-8", body.length());
                }
                out.write(body);
                out.flush();
            }

        } catch (IOException exception) {
            logger.log(Level.WARNING, "Error talking to " + connection.getRemoteSocketAddress(), exception);

        } finally {
            try {
                connection.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void sendHeader(Writer out, String responseCode, String contentType, int length) throws IOException {
        out.write(responseCode + "\r\n");
        Date now = new Date();
        out.write("Date: " + now + "\r\n");
        out.write("Server: JHTTP 2.0\r\n");
        out.write("Content-length: " + length + "\r\n");
        out.write("Content-type: " + contentType + "\r\n\r\n");
        out.flush();
    }

}
