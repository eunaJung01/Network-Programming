package Lecture.week14;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPDiscardServer {

    private final static int PORT = 9;
    private final static int MAX_PACKET_SIZE = 65507;

    public static void main(String[] args) {
        byte[] buffer = new byte[MAX_PACKET_SIZE];

        try (DatagramSocket server = new DatagramSocket(PORT)) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {
                try {
                    server.receive(packet); // blocking mode

                    String s = new String(packet.getData(), 0, packet.getLength(), "8859_1");
                    System.out.println(packet.getAddress() + " at port " + packet.getPort() + " says " + s);

                    // reset the length for the next packet
                    System.out.println(packet.getLength());
                    packet.setLength(buffer.length); // 굳이 필요한가? (이 예제에서는 굳이 필요한 것 같진 않음)

                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            } // end while

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

}
