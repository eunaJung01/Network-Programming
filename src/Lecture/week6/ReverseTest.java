package Lecture.week6;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ReverseTest {

    public static void main(String[] args) {
        try {
            InetAddress ia = InetAddress.getByName("202.30.38.108");
            System.out.println(ia.getHostName());
            System.out.println(ia.getCanonicalHostName());

            InetAddress ia2 = InetAddress.getByName("www.konkuk.ac.kr");
            System.out.println(ia2);
            System.out.println(ia2.getCanonicalHostName());

            /*
            202.30.38.108
            202.30.38.108
            www.konkuk.ac.kr/202.30.38.108
            202.30.38.108
             */

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
