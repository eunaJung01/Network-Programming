package Lecture.week12;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JHTTP {

    private static final Logger logger = Logger.getLogger(JHTTP.class.getCanonicalName());
    private static final int NUM_THREADS = 50;
    private static final String INDEX_FILE = "logo.png";

    private final File rootDirectory;
    private final int port;

    public JHTTP(File rootDirectory, int port) throws IOException {
        if (!rootDirectory.isDirectory()) {
            throw new IOException(rootDirectory + " does not exist as a directory");
        }
        this.rootDirectory = rootDirectory;
        this.port = port;
    }

    public void start() throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);

        try (ServerSocket server = new ServerSocket(port)) {
            logger.info("Accepting connections on port " + server.getLocalPort());
            logger.info("Document Root: " + rootDirectory);

            while (true) {
                try {
                    Socket request = server.accept();
                    Runnable r = new RequestProcessor(rootDirectory, INDEX_FILE, request);
                    pool.submit(r);

                } catch (IOException exception) {
                    logger.log(Level.WARNING, "Error accepting connection", exception);
                }
            }
        }
    }

    public static void main(String[] args) {
        // get the Document root
        File docroot;
        try {
            docroot = new File("/Users/euna/Desktop/KU/WorkSpace/NetworkProgramming");
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Usage: java JHTTP docroot port");
            return;
        }

        // set the port to listen on
        int port;
        try {
            port = 80;
            if (port < 0 || port > 65535) port = 80;
        } catch (RuntimeException exception) {
            port = 80;
        }

        try {
            JHTTP webserver = new JHTTP(docroot, port);
            webserver.start();
        } catch (IOException exception) {
            logger.log(Level.SEVERE, "Server could not start", exception);
        }
    }

}
