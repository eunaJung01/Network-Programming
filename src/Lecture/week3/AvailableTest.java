package Lecture.week3;

import java.io.IOException;

public class AvailableTest {

    public static void main(String[] args) {
        try {
            byte[] b = new byte[System.in.available()];
            System.in.read(b);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
