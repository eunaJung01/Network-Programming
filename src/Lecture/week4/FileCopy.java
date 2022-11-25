package Lecture.week4;

import java.io.FileReader;
import java.io.FileWriter;

public class FileCopy {

    public static void main(String[] args) {
//        if (args.length != -1) {
//            System.out.println("사용법 : java StreamReaderTest 파일명");
//            System.exit(0);
//        }

        FileReader fr = null;
        FileWriter fw = null;

        try {
            fr = new FileReader("src/Lecture.week4/StreamReaderWriterTest.java"); // src
            fw = new FileWriter("CopyStreamReaderWriterTest.java"); // destination

            char[] buffer = new char[512];
            int readcount = 0;
            while ((readcount = fr.read(buffer)) != -1) {
                fw.write(buffer, 0, readcount); // default encoding → 이 파일 내용을 다른 곳에서 가져다가 읽을 경우 깨질수도 있음
            }
            System.out.println("파일을 복사하였습니다");

            fr.close();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
