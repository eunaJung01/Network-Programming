package Lecture.week7;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SourceViewerSimpleTest {

    public static void main(String[] args) {
        InputStream in = null;
        try {
//            URL u = new URL("https://www.lolcats.com");
//            URL u = new URL("https://www.lolcats.com");
//            URL u = new URL("http://home.konkuk.ac.kr/~leehw/Site/nptest/files/logo.png");
            URL u = new URL("http://home.konkuk.ac.kr/~leehw/Site/nptest/files/hello.html");
            System.out.println(u);

            // raw HTML (raw content를 그대로 읽어옴)
            in = u.openStream();
            int c;
            while ((c = in.read()) != -1) System.out.write(c);

        } catch (IOException e) {
            System.err.println(e);

        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
            }
        }
    }

}
