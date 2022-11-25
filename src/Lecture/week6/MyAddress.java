package Lecture.week6;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyAddress {

    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address);
            System.out.println(address.getHostAddress()); // 127.0.0.1 : lookback address, localhost
            // 내 컴퓨터 안에 web server를 만들어놓고, 내 컴퓨터에서 직접 접속하려고 할 때 (외부 접속 없이) 주소창에 해당 address 또는 localhost를 치면 됨

        } catch (UnknownHostException e) {
            System.out.println("Could not find this computer's address");
        }
    }

}
