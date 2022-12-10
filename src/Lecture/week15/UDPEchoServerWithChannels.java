package Lecture.week15;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UDPEchoServerWithChannels {

    public final static int PORT = 7;
    public final static int MAX_PACKET_SIZE = 65507;

    public static void main(String[] args) {
        try {
            DatagramChannel channel = DatagramChannel.open();
            DatagramSocket socket = channel.socket();
            SocketAddress address = new InetSocketAddress(PORT);
            socket.bind(address);

            ByteBuffer buffer = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);
            while (true) {
                SocketAddress client = channel.receive(buffer);
                buffer.flip();
                channel.send(buffer, client);
                buffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

/*
 * this runs in blocking mode
 * server doesn't wait for client to be ready
 * much less opportunity for one client to get held up behind slow client
 * blocking mode not a big problem for UDP
 */
