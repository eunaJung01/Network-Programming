package Lecture.week10;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class SourceViewer4 {

    public static void main(String[] args) {
        try {
//            URL u = new URL("http://www.ibiblio.org/gonewalkabout");
//            URL u = new URL("https://www.konkuk.ac.kr/abc.html");
            URL u = new URL("http://ecampus.konkuk.ac.kr/abc.html");
//            URL u = new URL("https://oreilly.com/abc.html");

            // Open the URLConnection for reading
            HttpURLConnection uc = (HttpURLConnection) u.openConnection();
            System.out.println(uc.getResponseCode());
            System.out.println();

            try (InputStream raw = uc.getInputStream()) {
                printFromStream(raw);
            } catch (IOException exception) {
                printFromStream(uc.getErrorStream());
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void printFromStream(InputStream raw) throws IOException {
        try (InputStream buffer = new BufferedInputStream(raw)) {
            Reader reader = new InputStreamReader(buffer);
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
        }
    }

}
