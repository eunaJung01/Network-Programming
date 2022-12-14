package Lecture.week12;

import java.io.IOException;
import java.net.ServerSocket;

public class LocalPortScanner {

    public static void main(String[] args) {
        for (int port = 1; port <= 1024; port++) {
            try {
                // the next line will fail and drop into the catch block
                // if there is already a server running on the port
                ServerSocket server = new ServerSocket(port);

            } catch (IOException exception) {
                System.out.println("There is a server on port " + port + ".");
            }
        }
    }

}
