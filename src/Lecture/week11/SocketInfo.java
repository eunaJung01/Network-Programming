package Lecture.week11;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SocketInfo {

    public static void main(String[] args) {
        String host = "www.konkuk.ac.kr";

        try {
            Socket theSocket = new Socket(host, 80);
            System.out.println("Connected to " + theSocket.getInetAddress()
                    + " on port " + theSocket.getPort() + " from port " + theSocket.getLocalPort()
                    + " of " + theSocket.getLocalAddress());

            // Connected to www.konkuk.ac.kr/202.30.38.108 on port 80 from port 60483 of /172.30.1.5
            // port : OS가 임의로 할당해주었음

        } catch (UnknownHostException e) {
            System.err.println("I can't find " + host);
        } catch (SocketException ex) {
            System.err.println("Could not connect to " + host);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
