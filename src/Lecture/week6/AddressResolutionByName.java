package Lecture.week6;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AddressResolutionByName {

    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.konkuk.ac.kr"); // DNS에 contact해서 IP resolution을 한 것
            System.out.println(address);
//            displayInetAddressInformation(address);

            InetAddress address2 = InetAddress.getByName("202.30.38.108");
            System.out.println(address2); // no DNS contact - reverse lookup까지는 하지 않음 (reverse lookup : IP주소를 통해 hostname을 알아내는 것)
            System.out.println(address2.getHostName()); // do DNS contact - reverse lookup -> winter.konkuk.ac.kr (???)
            // "www.konkuk.ac.kr" & "winter.konkuk.ac.kr" - 같은 IP 주소를 사용하는 서버에 내용들이 같이 들어가있음
            // -> DNS server canonical name (CNAME) ??

            InetAddress[] addresses = InetAddress.getAllByName("www.yahoo.com"); // 하나의 hostname이 여러 개의 IP 주소로 mapping 될 수 있다!
            for (InetAddress address3 : addresses) {
                System.out.println(address3);
            }

        } catch (UnknownHostException e) {
            System.out.println("Could not find www.konkuk.ac.kr");
        }
    }

//    private static void displayInetAddressInformation(InetAddress address) {
//        System.out.println(address);
//        System.out.println("CanonicalHostName: " + address.getCanonicalHostName());
//        System.out.println("HostName: " + address.getHostName());
//        System.out.println("HostAddress: " + address.getHostAddress());
//    }

}
