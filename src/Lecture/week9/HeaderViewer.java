package Lecture.week9;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class HeaderViewer {

    public static void main(String[] args) {
        try {
            URL u = new URL("http://ecampus.konkuk.ac.kr");
            URLConnection uc = u.openConnection();

            System.out.println("Content-type: " + uc.getContentType());

            if (uc.getContentEncoding() != null) {
                System.out.println("Content-encoding: " + uc.getContentEncoding());
            }

            if (uc.getDate() != 0) {
                System.out.println("Date: " + new Date(uc.getDate()));
            }

            if (uc.getLastModified() != 0) {
                System.out.println("Last Modified: " + new Date(uc.getExpiration()));
            }

            if (uc.getExpiration() != 0) {
                System.out.println("Expiration date: " + new Date(uc.getExpiration()));
            }

            if (uc.getContentLength() != -1) {
                System.out.println("Content-length: " + uc.getContentLength());
            }

        } catch (MalformedURLException exception) {
            System.out.println(args[0] + " is not a URL I understand");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

}
