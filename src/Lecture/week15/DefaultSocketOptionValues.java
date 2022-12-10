package Lecture.week15;

import java.io.IOException;
import java.net.SocketOption;
import java.nio.channels.DatagramChannel;

public class DefaultSocketOptionValues {

    public static void main(String[] args) {
        try (DatagramChannel channel = DatagramChannel.open()) {
            for (SocketOption<?> option : channel.supportedOptions()) {
                System.out.println(option.name() + ": " + channel.getOption(option));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}

/*
SO_SNDBUF: 9216
SO_BROADCAST: false
IP_MULTICAST_LOOP: true
IP_TOS: 0
SO_REUSEADDR: false
IP_MULTICAST_TTL: 1
SO_RCVBUF: 786896
IP_MULTICAST_IF: null
 */
