package Homework.week12;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

    public final static int PORT = 7;
    private static final Scanner scan = new Scanner(System.in);

    private static OutputStream out;
    private static OutputStreamWriter writer;
    private static InputStream in;
    private static InputStreamReader reader;

    public static void main(String[] args) {

        String hostname = "localhost"; // 127.0.0.1
        Socket socket = null;

        try {
            socket = new Socket(hostname, PORT);
            socket.setSoTimeout(10000);

            // 1. send a string (from keyboard input) to server waiting on port 7
            send(socket);

            // 2. print the received (from server) string on the console
            receive(socket);

        } catch (IOException exception) {
            exception.printStackTrace();

        } finally {
            if (socket != null) {
                try {
                    socket.close();
                    out.close();
                    writer.close();
                    in.close();
                    reader.close();
                } catch (IOException exception) {
                    // ignore
                }
            }
        }
    }

    private static void send(Socket socket) throws IOException {
        out = socket.getOutputStream();
        writer = new OutputStreamWriter(out, "ASCII");
        String userInput = scan.nextLine();
        writer.write(userInput + "\r\n");
        writer.flush();
        System.out.println("EchoClient sent userInput: " + userInput);
    }

    private static void receive(Socket socket) throws IOException {
        in = socket.getInputStream();
        StringBuilder userInput = new StringBuilder();
        reader = new InputStreamReader(in, "ASCII");
        for (int c = reader.read(); c != -1; c = reader.read()) {
            userInput.append((char) c);
        }
        System.out.println("EchoClient received userInput: " + userInput);
    }

}
