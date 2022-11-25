package Lecture.week10;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class LastModified {

    public static void main(String[] args) {
        try {
//            URL u = new URL("https://www.google.com");
//                URL u = new URL("https://www.kyobobook.co.kr");
                URL u = new URL("https://ecampus.konkuk.ac.kr");

            HttpURLConnection http = (HttpURLConnection) u.openConnection();
//            http.setRequestMethod("HEAD");
            http.setRequestMethod("GET");
            System.out.println(u + " was last modified at " + new Date(http.getLastModified()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
