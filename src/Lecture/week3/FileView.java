package Lecture.week3;

import java.io.FileInputStream;
import java.io.IOException;

public class FileView {

    public static void main(String[] args) {
        fileView();
    }

    public static void fileView() {
        long start = System.currentTimeMillis();

//        if (args.length != 1) {
//            System.out.println("사용법 : java FileView 파일이름");
//            System.exit(0);
//        }

        FileInputStream fis = null;

        // 1
        //*
        try {
            fis = new FileInputStream("yee.txt");
            int i = 0;
            while ((i = fis.read()) != -1) { // source를 I/O device로부터 1 byte씩 읽어들임
                System.out.write(i); // in ASCII (숫자를 출력하고 싶으면 print)
            }

        } catch (Exception exception) {
            System.out.println(exception);
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
            }
        }
        //*/

        // 2 -> faster
        /*
        try {
            fis = new FileInputStream("yee.txt");

            int readcount = 0;
            byte[] buffer = new byte[512]; // read 512 bytes at a time

            while ((readcount = fis.read(buffer)) != -1) {
                System.out.write(buffer, 0, readcount);
            }

        } catch (Exception exception) {
            System.out.println(exception);
        } finally {
            try {
                fis.close();
            } catch (IOException e) {

            }
        }
        //*/

        long end = System.currentTimeMillis();
        System.out.println("Run-time : " + (end - start));
    }

}
