package Lecture.week10;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class LastModifiedExercise {

    public static void main(String[] args) {
        try {
//            URL u = new URL("https://www.google.com");
            URL u = new URL("https://www.kyobobook.co.kr");
//            URL u = new URL("http://ecampus.konkuk.ac.kr");
//            URL u = new URL("http://www.konkuk.ac.kr");
//            URL u = new URL("https://www.lolcats.com");

            URLConnection uc = u.openConnection();
            HttpURLConnection http = (HttpURLConnection) uc;

//            http.setRequestMethod("DELETE");
//            http.setRequestMethod("PUT"); // HTTP/1.1 411 Length Required
            http.setRequestMethod("OPTIONS"); // allow: GET,HEAD,OPTIONS

            // print all the header fields
            System.out.println(uc.getHeaderField(0)); // header의 첫번째 line을 가져오는 것
            for (int j = 1; ; j++) {
                String header = uc.getHeaderField(j);
                String key = uc.getHeaderFieldKey(j);
                if (header == null || key == null) break;
                System.out.println(uc.getHeaderFieldKey(j) + ": " + header);
            }
            System.out.println();

            // print content
            InputStream raw = http.getInputStream();
            InputStream buffer = new BufferedInputStream(raw);
            Reader reader = new InputStreamReader(buffer);
            BufferedReader br = new BufferedReader(reader);

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

}
