package Lecture.week7;

import java.awt.image.ImageProducer;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

public class ContentTypePerferenceTest {

    public static void main(String[] args) {
        try {
//            URL u = new URL("https://www.oreilly.com"); // "I got a sun.net.www.protocol.http.HttpURLConnection$HttpInputStream"
            URL u = new URL("http://home.konkuk.ac.kr/~leehw/Site/nptest/files/logo.png");
            // null object -> ImageProducer.class 추가 & "I got a sun.awt.image.URLImageSource"

            // priority
            Class<?>[] types = new Class[4];
            types[0] = String.class;
            types[1] = Reader.class;
            types[2] = InputStream.class;
            types[3] = ImageProducer.class;

            Object o = u.getContent(types);
            if (o == null) {
                System.out.println("null object");
            }
            assert o != null;
            System.out.println("I got a " + o.getClass().getName());

            if (o instanceof String) {
                System.out.println(o);

            } else if (o instanceof Reader) {
                int c;
                Reader r = (Reader) o;
                while ((c = r.read()) != -1) System.out.println((char) c);
                r.close();

            } else if (o instanceof InputStream) {
                int c;
                InputStream in = (InputStream) o;
                while ((c = in.read()) != -1) System.out.write(c);
                in.close();

            } else if (o instanceof ImageProducer) {
                System.out.println("ImageProducer returned");

            } else {
                System.out.println("Error: unexpected type " + o.getClass());
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
