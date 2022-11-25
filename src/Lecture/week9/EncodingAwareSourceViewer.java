package Lecture.week9;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class EncodingAwareSourceViewer {

    public static void main(String[] args) {
        try {
            // set default encoding
            String encoding = "ISO-8859-1";
            URL u = new URL("http://ecampus.konkuk.ac.kr"); // 203.252.142.197

            System.out.println("openConnection()");
            URLConnection uc = u.openConnection();
            Thread.sleep(7000);

            System.out.println("getContentType()");
            String contentType = uc.getContentType(); // -> 여기서 connection이 open됨
            Thread.sleep(7000);

            System.out.println("encodingStart");
            int encodingStart = contentType.indexOf("charset=");
            Thread.sleep(7000);

            if (encodingStart != -1) {  // "charset"이라는 field가 존재한다면
                encoding = contentType.substring(encodingStart + 8);
            }

            System.out.println();
            System.out.println(contentType); // text/html;charset=utf-8
            System.out.println(encoding); // utf-8

            InputStream in = new BufferedInputStream(uc.getInputStream());
            Reader r = new InputStreamReader(in, encoding);
            int c;
            while ((c = r.read()) != -1) {
                System.out.print((char) c);
            }
            r.close();

        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
    }

}
