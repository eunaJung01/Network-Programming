package Homework.week12;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public final static int PORT = 7;

    public static InputStream in;
    public static InputStreamReader reader;
    public static BufferedReader bufferedReader;

    public static void main(String[] args) throws IOException {

        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                try (Socket connection = server.accept()) { // return Socket 객체
                    in = connection.getInputStream();
                    reader = new InputStreamReader(in, "ASCII");
                    bufferedReader = new BufferedReader(reader);

                    String userInput = bufferedReader.readLine();
                    System.out.println("EchoServer read userInput: " + userInput);

                    Writer out = new OutputStreamWriter(connection.getOutputStream());
                    out.write(userInput + "\r\n");
                    out.flush();

                } catch (IOException exception) {
                    // ignore
                }
            }

        } catch (IOException exception) {
            exception.printStackTrace();

        } finally {
            in.close();
            reader.close();
            bufferedReader.close();
        }
    }

}
