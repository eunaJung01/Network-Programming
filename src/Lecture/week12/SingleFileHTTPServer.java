package Lecture.week12;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingleFileHTTPServer {

    private static final Logger logger = Logger.getLogger("SingleFileHTTPServer");

    private final byte[] content; // 보낼 content
    private final byte[] header; // response header
    private final int port; // 80
    private final String encoding;

    public SingleFileHTTPServer(String data, String encoding, String mimeType, int port) throws UnsupportedEncodingException {
        this(data.getBytes(encoding), encoding, mimeType, port);
    }

    public SingleFileHTTPServer(byte[] data, String encoding, String mimeType, int port) {
        this.content = data;
        this.port = port;
        this.encoding = encoding;
        String header = "HTTP/1.0 200 OK\r\n" + "Server: OneFile 2.0\r\n" + "Content-length: " + this.content.length + "\r\n" + "Content-type: " + mimeType + "; charset=" + encoding + "\r\n\r\n";
        this.header = header.getBytes(StandardCharsets.US_ASCII);
    }

    public void start() {
        ExecutorService pool = Executors.newFixedThreadPool(100);
        try (ServerSocket server = new ServerSocket(this.port)) {
            logger.info("Accepting connections on port " + server.getLocalPort());
            logger.info("Data to be sent:");
            logger.info(new String(this.content, encoding));

            while (true) {
                try {
                    Socket connection = server.accept();
                    pool.submit(new HTTPHandler(connection));

                } catch (IOException exception) {
                    logger.log(Level.WARNING, "Exception accepting connection", exception);
                } catch (RuntimeException exception) {
                    logger.log(Level.SEVERE, "Unexpected error", exception);
                }
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private class HTTPHandler implements Callable<Void> {
        private final Socket connection;

        public HTTPHandler(Socket connection) {
            this.connection = connection;
        }

        @Override
        public Void call() throws IOException {
            try {
                OutputStream out = new BufferedOutputStream(connection.getOutputStream());
                InputStream in = new BufferedInputStream(connection.getInputStream());

                // read the first line only; that's all we need
                StringBuilder request = new StringBuilder(80);
                while (true) {
                    int c = in.read();
                    if (c == '\r' || c == '\n' || c == -1) break;
                    request.append((char) c);
                }

                // If this is HTTP/1.0 or later send a MIME header
                if (request.toString().contains("HTTP/")) {
                    out.write(header);
                }
                out.write(content);
                out.flush();

            } catch (IOException exception) {
                logger.log(Level.WARNING, "Error writing to client", exception);

            } finally {
                connection.close();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        // set the port to listen on
        int port;
        try {
            port = 80;
            if (port < 1 || port > 65535) port = 80;

        } catch (RuntimeException exception) {
            port = 80;
        }

        String encoding = "UTF-8";
        String fileName = "/Users/euna/Desktop/KU/WorkSpace/NetworkProgramming/logo.png";

        try {
            Path path = Paths.get(fileName);
            byte[] data = Files.readAllBytes(path);

            String contentType = URLConnection.getFileNameMap().getContentTypeFor(fileName);
            SingleFileHTTPServer server = new SingleFileHTTPServer(data, encoding, contentType, port
            );
            server.start();

        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Usage: java SingleFileHTTPServer filename port encoding");
        } catch (IOException exception) {
            logger.severe(exception.getMessage());
        }
    }

}
