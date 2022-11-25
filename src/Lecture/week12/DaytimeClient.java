package Lecture.week12;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class DaytimeClient {

    public static void main(String[] args) {
        String hostname = "localhost"; // 127.0.0.1
        Socket socket = null;

        try {
            socket = new Socket(hostname, 13);
            socket.setSoTimeout(10000);

            InputStream in = socket.getInputStream();
            StringBuilder time = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in, "ASCII");
            for (int c = reader.read(); c != -1; c = reader.read()) {
                System.out.println(c);
                time.append((char) c);
            }
            System.out.println(time);

        } catch (IOException exception) {
            exception.printStackTrace();

        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException exception) {
                    // ignore
                }
            }
        }
    }

}
