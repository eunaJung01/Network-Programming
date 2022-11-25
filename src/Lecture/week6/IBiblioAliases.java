package Lecture.week6;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IBiblioAliases {

    public static void main(String[] args) {
        try {
            InetAddress ia1 = InetAddress.getByName("www.konkuk.ac.kr");
            InetAddress ia2 = InetAddress.getByName("winter.konkuk.ac.kr");

            if (ia1.equals(ia2)) {
                System.out.println(ia1.getHostName() + " is the same as " + ia2.getHostName());
            } else {
                System.out.println(ia1.getHostName() + " is not the same as " + ia2.getHostName());

            }
        } catch (UnknownHostException e) {
            System.out.println("Host lookup failed");
        }
    }

}
