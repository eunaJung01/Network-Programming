package Lecture.week14;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class DaytimeUDPClient {

    private final static int PORT = 13;
    private static final String HOSTNAME = "localhost";

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(0)) { // UDP Socket
            socket.setSoTimeout(10000);

            InetAddress host = InetAddress.getByName(HOSTNAME);
            DatagramPacket request = new DatagramPacket(new byte[1], 1, host, PORT);
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);

            socket.send(request);
            socket.receive(response);

            String result = new String(response.getData(), 0, response.getLength(), StandardCharsets.US_ASCII);
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
