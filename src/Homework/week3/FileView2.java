package Homework.week3;

import java.io.FileInputStream;
import java.io.IOException;

public class FileView2 {

    public static void main(String[] args) {
        fileView2();
    }

    static void fileView2() {
        long start = System.currentTimeMillis();

//        if (args.length != 1) {
//            System.out.println("사용법 : java FileView 파일이름");
//            System.exit(0);
//        }

        FileInputStream fis = null;

        try {
            fis = new FileInputStream("yee.txt");
            int readcount = 0;
            byte[] buffer = new byte[512];

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

        long end = System.currentTimeMillis();
        System.out.println("Run-time : " + (end - start));
    }

}
