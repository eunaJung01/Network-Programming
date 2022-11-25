package Lecture.week7;

import java.io.*;
import java.net.Authenticator;
import java.net.URL;

public class SecureSourceViewer {

    public static void main(String[] args) {
        Authenticator.setDefault(new DialogAuthenticator());
        // setDefault()가 호출되면, 만약 이 URL이 가리키는 리소스가 password를 요구한다면
        // DialogAuthenticator에게 getPasswordAuthentication()를 이용하여 물어봄
        // -> 만든 dialog가 뜨게 됨

        for (int i = 0; i < args.length; i++) {
            try {
                // Open the URL for reading
                URL u = new URL(args[i]);

                try (InputStream in = new BufferedInputStream(u.openStream())) {
                    // chain the InputStream to a Reader
                    Reader r = new InputStreamReader(in);
                    int c;
                    while ((c = r.read()) != -1) {
                        System.out.println((char) c);
                    }
                }
            } catch (IOException e) {
                System.err.println(e);
            }

            // print a blank line to separate pages
            System.out.println();
        }
        // Since we used the AWT, we have to explicitly exit
        System.exit(0);
    }

}
