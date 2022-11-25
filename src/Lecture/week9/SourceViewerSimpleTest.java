package Lecture.week9;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SourceViewerSimpleTest {

    public static void main(String[] args) {
        InputStream in = null;
        try {
            URL u = new URL("http://home.konkuk.ac.kr/~leehw/Site/nptest/files/logo.png");
            System.out.println(u);
            in = u.openStream();
            int c;
            while ((c = in.read()) != -1) System.out.write(c);

        } catch (IOException ex) {
            System.err.println(ex);

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
