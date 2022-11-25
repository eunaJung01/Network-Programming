package Lecture.week3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SystemInReadOutWriteTest {

    public static void main(String[] args) {
        InputStream in = System.in;
        OutputStream out = System.out;

        try {
            // 17 입력 시 앞의 '1'만 읽음 -> ASCII 49번

            int input = in.read(); // read 1 byte
            System.out.println(input);

            out.write(input); // write a single unsigned byte
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
