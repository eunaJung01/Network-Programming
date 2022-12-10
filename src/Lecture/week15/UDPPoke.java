package Lecture.week15;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

// TODO: this class makes it easy to write a UDP client for daytime, time, chargen, ...
// 1. send an empty UDP pakcet to a specified host and port
// 2. read a response packet from the same host
public class UDPPoke {

    private int bufferSize; // in bytes
    private int timeout; // in milliseconds
    private InetAddress host;
    private int port;

    public UDPPoke(InetAddress host, int port, int bufferSize, int timeout) {
        this.bufferSize = bufferSize;
        this.host = host;
        if (port < 1 || port > 6535) {
            throw new IllegalArgumentException("Port out of range");
        }
        this.port = port;
        this.timeout = timeout;
    }

    public UDPPoke(InetAddress host, int port, int bufferSize) {
        this(host, port, bufferSize, 30000);
    }

    public UDPPoke(InetAddress host, int port) {
        this(host, port, 8192, 300000);
    }

    public byte[] poke() {
        // create socket and datagram packet
        try (DatagramSocket socket = new DatagramSocket(0)) {
            DatagramPacket outgoing = new DatagramPacket(new byte[1], 1, host, port);
            socket.connect(host, port);
            socket.setSoTimeout(timeout);
            socket.send(outgoing); // send it to host

            DatagramPacket incoming = new DatagramPacket(new byte[bufferSize], bufferSize);
            // next line blocks until the response is received
            socket.receive(incoming);

            int numBytes = incoming.getLength();
            byte[] response = new byte[numBytes];
            System.arraycopy(incoming.getData(), 0, response, 0, numBytes);
            return response; // return bytes read from host

        } catch (IOException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        InetAddress host;
        int port = 0;

        try {
            host = InetAddress.getByName(args[0]);
            port = Integer.parseInt(args[1]);

        } catch (UnknownHostException e) {
            System.out.println("Usage: java UDPPoke host port");
            return;
        }

        UDPPoke poker = new UDPPoke(host, port);
        byte[] response = poker.poke();
        if (response == null) {
            System.out.println("No response within allotted time");
            return;
        }
        String result = new String(response, StandardCharsets.US_ASCII);
        System.out.println(result);
    }

}
