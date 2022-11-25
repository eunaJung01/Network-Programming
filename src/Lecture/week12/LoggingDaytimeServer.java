package Lecture.week12;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingDaytimeServer {

    public final static int PORT = 13;
    private final static Logger auditLogger = Logger.getLogger("requests");
    private final static Logger errorLogger = Logger.getLogger("errors");

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(50);

        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                try {
                    Socket connection = server.accept();
                    Callable<Void> task = new DaytimeTask(connection);
                    pool.submit(task);

                } catch (IOException exception) {
                    errorLogger.log(Level.SEVERE, "accept error", exception);
                } catch (RuntimeException exception) {
                    errorLogger.log(Level.SEVERE, "unexpected error" + exception.getMessage(), exception);
                }
            }
        } catch (IOException exception) {
            errorLogger.log(Level.SEVERE, "Couldn't start server", exception);
        } catch (RuntimeException exception) {
            errorLogger.log(Level.SEVERE, "Couldn't start server" + exception.getMessage(), exception);
        }
    }

    private static class DaytimeTask implements Callable<Void> {

        private Socket connection;

        DaytimeTask(Socket connection) {
            this.connection = connection;
        }

        @Override
        public Void call() {
            try {
                Date now = new Date();
                // write the log entry first in case the client disconnects
                auditLogger.info(now + " " + connection.getRemoteSocketAddress());

                Writer out = new OutputStreamWriter(connection.getOutputStream());
                out.write(now.toString() + "\r\n");
                out.flush();

            } catch (IOException exception) {
                exception.printStackTrace();

            } finally {
                try {
                    connection.close();
                } catch (IOException exception) {
                    // ignore
                }
            }
            return null;
        }
    }

}
