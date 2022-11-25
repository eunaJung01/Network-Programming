package Lecture.week6;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class HardwareAddressTest {

    public static void main(String[] args) throws UnknownHostException, SocketException {

//        InetAddress address = InetAddress.getLocalHost();
        InetAddress address = InetAddress.getByName("172.30.1.5");

        System.out.println("IP address: " + address.getHostAddress());
        NetworkInterface ni = NetworkInterface.getByInetAddress(address);
        System.out.println("MAC address: " + getMACIdentifier(ni));
    }

    public static String getMACIdentifier(NetworkInterface ni) {
        StringBuilder identifier = new StringBuilder();
        try {
            byte[] macBuffer = ni.getHardwareAddress();
            if (macBuffer != null) {
                for (int i = 0; i < macBuffer.length; i++) {
                    identifier.append(String.format("%02X%s", macBuffer[i], // X -> macBuffer를 16진수로 읽어들임
                            (i < macBuffer.length - 1) ? "-" : ""));
                }
            } else {
                return "---";
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }
        return identifier.toString();
    }

}
