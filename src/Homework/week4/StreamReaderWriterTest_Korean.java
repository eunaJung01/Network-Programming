package Homework.week4;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class StreamReaderWriterTest_Korean {

    public static void main(String[] args) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;

        try {
            // working example (Korean) : aralla.txt
            fis = new FileInputStream("aralla.txt");

            // encodings
//            isr = new InputStreamReader(fis, "EUC-KR"); // 깨짐
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);

            osw = new OutputStreamWriter(System.out, StandardCharsets.UTF_8);

            char[] buffer = new char[512];
            int readcount = 0;
            while ((readcount = isr.read(buffer)) != -1) {
                osw.write(buffer, 0, readcount);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
            }
            try {
                isr.close();
            } catch (IOException e) {
            }
            try {
                osw.close();
            } catch (IOException e) {
            }
        }
    }

}
