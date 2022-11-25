package Lecture.week3;

import java.io.IOException;

import static java.lang.System.in;

public class SkipTest {

    public static void main(String[] args) throws IOException {
        long bytesSkipped = 0;
        long bytesToSkip = 80;

        while (bytesSkipped < bytesToSkip) {
            long n = in.skip(bytesToSkip - bytesSkipped);
            if (n == -1) break;
            bytesSkipped += n;
        }
    }

}
