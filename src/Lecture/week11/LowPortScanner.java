package Lecture.week11;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class LowPortScanner {

    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
//        String host = "www.konkuk.ac.kr";

        for (int i = 5; i < 1024; i++) {
            try {
                Socket s = new Socket(host, i);
                // 열려 있고, connection setup이 가능한 port에 대해서만 출력이 됨
                System.out.println("There is a server on port " + i + " of " + host);
                s.close();

            } catch (UnknownHostException e) {
                e.printStackTrace();
                break;
            } catch (IOException exception) {
                // must not be a server on this port
//                exception.printStackTrace();
            }
        }
    }

}
