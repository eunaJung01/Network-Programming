package Lecture.week13;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class ChargenServer {

    public static int DEFAULT_PORT = 19;

    public static void main(String[] args) {
        int port;
        try {
            port = Integer.parseInt(args[0]);
        } catch (RuntimeException exception) {
            port = DEFAULT_PORT;
        }
        System.out.println("Listening for connections on port " + port);

        byte[] rotation = new byte[95 * 2]; // buffer containing two sequence copies of data
        for (byte i = ' '; i <= '~'; i++) {
            rotation[i - ' '] = i;
            rotation[i + 95 - ' '] = i;
        }

        ServerSocketChannel serverChannel;
        Selector selector;
        try {
            serverChannel = ServerSocketChannel.open(); // this doesn't listen to any port (until it's bound to a port)
            ServerSocket ss = serverChannel.socket();
            InetSocketAddress address = new InetSocketAddress(port); // retrieve server socket and bind it to a port
            ss.bind(address);
            // alternatively: serverChannel.bind(new InetSocketAddress(19));

            // TODO: Activating Nonblocking Mode
            serverChannel.configureBlocking(false);

            selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT); // ready to accept new connection?

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while (true) {
            try {
                selector.select(); // check
            } catch (IOException ex) {
                ex.printStackTrace();
                break;
            }

            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                try {
                    // TODO: Checking Operation Types
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        System.out.println("Accepted connection from " + client);

                        client.configureBlocking(false);
                        SelectionKey key2 = client.register(selector, SelectionKey.OP_WRITE);

                        // Set up the buffer for the client
                        ByteBuffer buffer = ByteBuffer.allocate(74);
                        buffer.put(rotation, 0, 72);
                        buffer.put((byte) '\r');
                        buffer.put((byte) '\n');
                        buffer.flip();
                        key2.attach(buffer);
                    }
                    // TODO: Writing Data onto Channel
                    else if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();

                        // Write data to client
                        ByteBuffer buffer = (ByteBuffer) key.attachment(); // get attachment
                        if (!buffer.hasRemaining()) { // no elements between position and limit
                            // Refill the buffer with the next line
                            // Figure out where the last line stated
                            buffer.rewind(); // move to position to zero

                            // Get the old first character
                            int first = buffer.get(); // read a byte (and increment position)

                            // Get ready to change the data in the buffer
                            buffer.rewind(); // move position to zero

                            // Find the new first characters position in rotation
                            int position = first - ' ' + 1; // start from "!" (+1 make it rotating)

                            // copy the data from rotation into the buffer
                            buffer.put(rotation, position, 72);

                            // Store a line break at the end of the buffer
                            buffer.put((byte) '\r');
                            buffer.put((byte) '\n');

                            // Prepare the buffer for writing
                            buffer.flip();
                        }
                        client.write(buffer); // this also updates buffer in the attachment of key
                    }
                } catch (IOException e) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException ignored) {
                    }
                }
            }
        }
    }

}
