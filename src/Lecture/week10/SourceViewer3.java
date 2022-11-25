package Lecture.week10;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class SourceViewer3 {

    public static void main(String[] args) {
        try {
            URL u = new URL("https://www.google.com");
//            URL u = new URL("https://www.kyobobook.co.kr");
//            URL u = new URL("http://ecampus.konkuk.ac.kr");
//            URL u = new URL("http://www.konkuk.ac.kr");
//            URL u = new URL("https://www.lolcats.com");

            // Open the URLConnection for reading
            HttpURLConnection uc = (HttpURLConnection) u.openConnection();
            int code = uc.getResponseCode();
            int response = uc.getResponseCode();
            System.out.println("HTTP/1.x " + code + " " + response);

            for (int j = 1; ; j++) {
                String header = uc.getHeaderField(j);
                String key = uc.getHeaderFieldKey(j);
                if (header == null || key == null) break;
                System.out.println(uc.getHeaderFieldKey(j) + ": " + header);
            }
            System.out.println();

            try (InputStream in = new BufferedInputStream(uc.getInputStream())) {
                // chain the InputStream to a Reader
                Reader r = new InputStreamReader(in);
                int c;
                while ((c = r.read()) != -1) {
                    System.out.print((char) c);
                }
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
