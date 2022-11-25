package Lecture.week7;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ContentGetter {

    public static void main(String[] args) {
        try {
            URL u = new URL("https://www.oreilly.com");
//            URL u = new URL("http://home.konkuk.ac.kr/~leehw/Site/nptest/files/logo.png"); // IMG source를 적절하게 읽어올 수 있는 InputStream이 필요!!
            Object o = u.getContent();

            int c;
            InputStream r = (InputStream) o;

            while ((c = r.read()) != -1) System.out.write((char) c);
            r.close();
            System.out.println("I got a " + o.getClass().getName()); // class name을 출력

        } catch (MalformedURLException exception) {
            System.err.println(args[0] = " is not parseable URL");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
