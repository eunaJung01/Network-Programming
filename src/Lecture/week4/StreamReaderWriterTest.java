package Lecture.week4;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class StreamReaderWriterTest {

    public static void main(String[] args) {
//        if (args.length != -1) {
//            System.out.println("사용법 : java StreamReaderTest 파일명");
//            System.exit(0);
//        }

        FileInputStream fis = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;

        try {
            //* working example (English)
            fis = new FileInputStream("yee.txt");
            isr = new InputStreamReader(fis);
            osw = new OutputStreamWriter(System.out, StandardCharsets.UTF_8);
            //*/

            /* working example (Korean): test3.txt created in Windows notepad
            fis = new FileInputStream("test3.txt");
            isr = new InputStreamReader(fis, "EUC-KR");
            osw = new OutputStreamWriter(System.out, StandardCharsets.UTF_8);
            //*/

            /* working example (ENG+Korean): test5.txt created in MAC TextEdit (saved as plain txt; encoding automatic)
            fis = new FileInputStream("test5.txt");
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            osw = new OutputStreamWriter(System.out, StandardCharsets.UTF_8);
            //*/

            /* failing example (Korean+English)
            fis = new FileInputStream("test4.rtf");
            isr = new InputStreamReader(fis, "EUC-KR");
            osw = new OutputStreamWriter(System.out);
            //*/

            char[] buffer = new char[512];
            int readcount = 0;
            while ((readcount = isr.read(buffer)) != -1) {
                osw.write(buffer, 0, readcount);
            }

            fis.close();
            isr.close();
            osw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
