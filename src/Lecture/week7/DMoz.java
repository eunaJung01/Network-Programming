package Lecture.week7;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class DMoz {

    public static void main(String[] args) {
        /* (1)
        String target = "";
        for (int i = 0; i < args.length; i++) {
            target += args[i] + " ";
        }
        target += target.trim();

        QueryString query = new QueryString();
        query.add("q", target);
        //*/

        //* (2)
        QueryString query = new QueryString();
        query.add("q", "Java I/O");
        //*/

        try {
            // (1)
//            URL u = new URL("http://www.dmoz-odp.org/search/" + query); // 301 Moved Permanently
//            URL u = new URL("https://search.yahoo.com/search?p=java&fr=yfp-t&fp=1&toggle=1&cop=mss&ei=UTF-8");

            // (2)
            URL u = new URL("https://www.dmoz-odp.org/search?" + query);

            System.out.println(u);
            System.out.println();

            try (InputStream in = new BufferedInputStream(u.openStream())) {
                InputStreamReader theHTML = new InputStreamReader(in);
                int c;
                while ((c = theHTML.read()) != -1) {
                    System.out.print((char) c);
                }
            }

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

}
