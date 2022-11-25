package Lecture.week4;

import java.io.*;

public class FileBufferedStreamCopy {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("사용법 : java FileView 파일이름1 파일이름2");
            System.exit(0);
        }

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(args[0]);
            fos = new FileOutputStream(args[1]);

            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            int readcount = 0;
            byte[] buffer = new byte[512];

            while ((readcount = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, readcount);
            }
            System.out.println("복사가 완료되었습니다");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
            }
            try {
                fos.close();
            } catch (IOException e) {
            }
        }
    }

}
