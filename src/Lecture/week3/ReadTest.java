package Lecture.week3;

import java.io.IOException;
import java.util.Arrays;

import static java.lang.System.in;

public class ReadTest {

    public static void main(String[] args) throws IOException {

        // Example 1 : System.in.read()
//        int inChar = 0;
//        System.out.print("Enter a Character : ");
//
//        try {
//            inChar = in.read();
//            System.out.write(inChar);
//            System.out.println();
//            System.out.println(inChar);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Example 2 : System.in.read(byte[] b, int off, int len)
        // ->  attempt to read len bytes of data into b
        int bytesRead = 0;
        int bytesToRead = 1024;
        byte[] input = new byte[bytesToRead];

        while (bytesRead < bytesToRead) {
            int result = in.read(input, bytesRead, bytesToRead - bytesRead);
            if (result == -1) {
                break;
            }
            bytesRead += result;
//            System.out.println("bytesRead = " + bytesRead);
//            System.out.println("bytesToRead = " + bytesToRead);
        }
        System.out.println(Arrays.toString(input));
    }

}
