package Lecture.week6;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ReachabilityTest {

    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("wein.konkuk.ac.kr");

            int timeout = 5000; // ms 단위 -> 5초 안에 가능?
            int ttl = 10;

            if (address.isReachable(timeout)) {
                System.out.println(address.getHostName() + " CAN BE reached within " + timeout + "ms");
            } else {
                System.out.println(address.getHostName() + " CANNOT BE reached within " + timeout + "ms");
            }

            // 왜 안되는가??
            // 웹 서버에서 어떤 웹 브라우저를 이용해서 접속하는지를 알 수 있음
            // 우리가 흔히 사용하는 웹 브라우저가 아닌, 프로그램을 통해서 접속하는 경우는 많은 경우에 막아놓음 (쉽게 공격을 당할 수 있기 때문에)

        } catch (UnknownHostException e) {
            System.err.println("unknown hostname");
        } catch (IOException e) {
            System.err.println("e");
        }
    }

}
