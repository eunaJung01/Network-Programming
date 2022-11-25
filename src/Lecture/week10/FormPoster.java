package Lecture.week10;

import Lecture.week7.QueryString;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FormPoster {

    private URL url;
    private QueryString query = new QueryString();

    public FormPoster(URL url) {
        if (!url.getProtocol().toLowerCase().startsWith("http")) {
            throw new IllegalArgumentException("Posting only works for http URLs");
        }
        this.url = url;
    }

    public void add(String name, String value) {
        query.add(name, value);
    }

    public URL getURL() {
        return this.url;
    }

    public InputStream post() throws IOException {

        // open the connection and prepare it to POST
        URLConnection uc = url.openConnection();
        uc.setDoOutput(true);
        try (OutputStreamWriter out = new OutputStreamWriter(uc.getOutputStream(), "UTF-8")) {

            // The POST line, the Content-type header,
            // and the Content-length headers are sent by the URLConnection.
            // We just need to send the data

            // write의 default method : POST
            out.write(query.toString()); // 쓰는 부분
            out.write("\r\n");
            out.flush();
        }
        // Return the response
        return uc.getInputStream();
    }

    public static void main(String[] args) {
        URL url = null;
        if (args.length > 0) {
            try {
                url = new URL(args[0]);
            } catch (MalformedURLException exception) {
                System.err.println("Usage: java FromPoster url");
            }
        } else {
            try {
                url = new URL("http://www.cafeaulait.org/books/jnp4/postquery.phtml");
            } catch (MalformedURLException exception) { // shouldn't happen
                System.err.println(exception);
                return;
            }
        }

        FormPoster poster = new FormPoster(url);
        poster.add("name", "Elliotte Rusty Harold");
        poster.add("email", "elharo@ibiblio.org");
        poster.add("want to", "go home");

        try (InputStream in = poster.post()) {
            // Read the response
            Reader r = new InputStreamReader(in);
            int c;
            while ((c = r.read()) != -1) {
                System.out.print((char) c);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
