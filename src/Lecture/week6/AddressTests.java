package Lecture.week6;

import java.net.*;

public class AddressTests {

    public static void main(String[] args) {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            String dottedQuad = ia.getHostAddress();
            System.out.println("My Address is " + dottedQuad); // My Address is 127.0.0.1
            System.out.println("This is IPv" + getVersion(ia)); // My Address is 127.0.0.1

        } catch (UnknownHostException e) {
            System.out.println("I'm sorry. I don't know my own address.");
        }
    }

    public static int getVersion(InetAddress ia) {
        byte[] address = ia.getAddress();
        if (address.length == 4) return 4;
        else if (address.length == 16) return 6;
        else return -1;
    }

}
