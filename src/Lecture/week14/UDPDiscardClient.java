package Lecture.week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class UDPDiscardClient {

    private final static int PORT = 9;

    public static void main(String[] args) {
        String hostname = args.length > 0 ? args[0] : "localhost";

        try (DatagramSocket theSocket = new DatagramSocket()) { // 입력인자가 없다면 port number -> OS가 알아서 할당해줌
            InetAddress server = InetAddress.getByName(hostname);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String theLine = userInput.readLine();
                if (theLine.equals(".")) break;
                byte[] data = theLine.getBytes();

                DatagramPacket theOutput = new DatagramPacket(data, data.length, server, PORT);
                theSocket.send(theOutput);
            } // end while

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
