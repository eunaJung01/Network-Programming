package Lecture.week9;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class SourceViewer2 {

    public static void main(String[] args) {
        try {
            // Open the URLConnection for reading
            URL u = new URL("https://www.oreilly.com");
            URLConnection uc = u.openConnection();

            try (InputStream raw = uc.getInputStream()) { // autoclose
                InputStream buffer = new BufferedInputStream(raw);

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
