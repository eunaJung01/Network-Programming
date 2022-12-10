package Lecture.week14;

import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPPortScanner {

    public static void main(String[] args) {
        for (int port = 1024; port <= 65535; port++) {
            try {
                // the next line will fail and drop into the catch block
                // if there is already a server running on port i
                DatagramSocket server = new DatagramSocket(port);
                server.close();

            } catch (SocketException e) {
                System.out.println("There is a server on port " + port + ".");
                /*
                There is a server on port 5353.
                There is a server on port 7777.
                There is a server on port 49997.
                There is a server on port 58918.
                There is a server on port 61781.
                There is a server on port 63474.
                 */
            }
        }
    }

}
