package Lecture.week10;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class SourceViewer2 {

    public static void main(String[] args) {
        try {
            // Open the URLConnection for reading
            URL u = new URL("https://www.oreilly.com");
            URLConnection uc = u.openConnection();

            uc.setConnectTimeout(1); // 1 millisec

            try (InputStream raw = uc.getInputStream()) { // autoclose
                InputStream buffer = new BufferedInputStream(raw);
                uc.setConnectTimeout(1); // 1 millisec
                // connection이 이미 establish 된 이후이기 때문에 exception이 발생하지 않음

                // chain the InputStream to a Reader
                Reader reader = new InputStreamReader(buffer);

                int c;
                while ((c = reader.read()) != -1) {
                    System.out.print((char) c);
                }
            }

        } catch (IOException exception) {
            System.err.println(exception);
        }
    }

}
