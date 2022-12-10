package Lecture.week14;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class NonblockingSingleFileHTTPServer {

    private ByteBuffer contentBuffer;
    private int port = 80;

    public NonblockingSingleFileHTTPServer(ByteBuffer data, String encoding, String MIMEType, int port) {
        this.port = port;
        String header = "HTTP/1.0 200 OK\r\n"
                + "Server: NonblockingSingleFileHTTPServer\r\n"
                + "Content-length: " + data.limit() + "\r\n"
                + "Content-type: " + MIMEType + "\r\n\r\n";
        byte[] headerData = header.getBytes(StandardCharsets.US_ASCII);

        ByteBuffer buffer = ByteBuffer.allocate(data.limit() + headerData.length);
        buffer.put(headerData);
        buffer.put(data);
        buffer.flip();
        this.contentBuffer = buffer;
    }

    public void run() throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverChannel.socket();
        Selector selector = Selector.open();

        InetSocketAddress localPort = new InetSocketAddress(port);
        serverSocket.bind(localPort);

        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select(); // check

            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();

                try {
                    // TODO: Checking Operation Types
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel channel = server.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);
                    }

                    // TODO: Writing Data onto Channel
                    else if (key.isWritable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        if (buffer.hasRemaining()) {
                            channel.write(buffer);
                        } else { // we're done
                            channel.close();
                        }
                    }

                    // TODO: Just Read Something
                    else if (key.isReadable()) {
                        // Don't bother trying to parse the HTTP header
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(4096);
                        channel.read(buffer);

                        // switch channel to write-only mode
                        key.interestOps(SelectionKey.OP_WRITE);
                        key.attach(contentBuffer.duplicate());
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

    public static void main(String[] args) {
//        if (args.length == 0) {
//            System.out.println("Usage: java NonblockingSingleFileHTTPServer file port encoding");
//            return;
//        }

        String f = "./aralla.txt";

        try {
            // read the single file to serve
            String contentType = URLConnection.getFileNameMap().getContentTypeFor(f);
            Path file = FileSystems.getDefault().getPath(f);
            byte[] data = Files.readAllBytes(file);
//            ByteBuffer input = ByteBuffer.wrap(data);

            // for korean text
            //*
            List<String> lines = Files.readAllLines(Paths.get(f), StandardCharsets.UTF_8);
            ByteBuffer input = ByteBuffer.allocate(data.length + 2 * lines.size());
            for (String element : lines) {
                input.put(element.getBytes("EUC-KR"));
                input.put("\r\n".getBytes());
                System.out.println(element);
            }
            input.flip();
            //*/

            // set the port to listen on
            int port = 80;
//            try {
//                port = Integer.parseInt(args[1]);
//                if (port < 1 || port > 65535) port = 80;
//            } catch (RuntimeException exception) {
//                port = 80;
//            }

            String encoding = "UTF-8";
            NonblockingSingleFileHTTPServer server = new NonblockingSingleFileHTTPServer(input, encoding, contentType, port);
            server.run();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
