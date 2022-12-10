package Lecture.week14;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class DatagramExample {

    public static void main(String[] args) {
        String s = "This is a test.";

        try {
            byte[] data = s.getBytes(StandardCharsets.UTF_8);
            InetAddress ia = InetAddress.getByName("www.ibiblio.org");

            int port = 7;
            DatagramPacket dp = new DatagramPacket(data, data.length, ia, port);

            System.out.println("This packet is addressed to " + dp.getAddress() + " on port " + dp.getPort()); // This packet is addressed to www.ibiblio.org/152.19.134.40 on port 7
            System.out.println("There are " + dp.getLength() + " bytes of data in the packet"); // There are 15 bytes of data in the packet
            System.out.println(new String(dp.getData(), dp.getOffset(), dp.getLength(), StandardCharsets.UTF_8)); // This is a test.

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
