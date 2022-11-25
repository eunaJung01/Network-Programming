package Lecture.week7;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class QueryStringUsage {

    public static void main(String[] args) {
        QueryString qs = new QueryString();
        // "h1", "as_q", "as_epq" 등 미리 알고 있다고 가정
        qs.add("h1", "en");
        qs.add("as_q", "Java");
        qs.add("as_epq", "I/O");

        String url = "https://www.google.com/search?" + qs;
        System.out.println(url);
        System.out.println();

        InputStream in = null;
        try {
//            URL u = new URL(url);
            // java.io.IOException: Server returned HTTP response code: 403 for URL
            // 403 Forbidden : 금지된 방법으로 access 하려고 했다
            // Server는 웹 브라우저가 어떤 놈인지를 확인 -> 프로그램을 돌려서 access 하는 형태는 전부 다 막음

            URL u = new URL("https://search.daum.net/search?w=tot&DA=YZR&t_nil_searchbox=btn&sug=&sugo=&q=Java+I%2F0");

            in = u.openStream();
            int c;
            while ((c = in.read()) != -1) System.out.write(c);


        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
