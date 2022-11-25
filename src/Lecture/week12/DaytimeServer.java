package Lecture.week12;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DaytimeServer {

    public final static int PORT = 13;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {

            while (true) {
                try (Socket connection = server.accept()) { // return Socket 객체
                    Writer out = new OutputStreamWriter(connection.getOutputStream());
                    Date now = new Date();
//                    out.write(now.toString() + "\r\n");
                    out.write(now.toString());
                    out.flush();
                    connection.close();

                } catch (IOException exception) {
                    // ignore
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
