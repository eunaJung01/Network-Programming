package Lecture.week7;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceViewer {

    public static void main(String[] args) {
        if (args.length > 0) {
            InputStream in = null;
            try {
                // Open the URL for reading
                URL u = new URL(args[0]);
                in = u.openStream();

                // buffer the input to increase performance
                in = new BufferedInputStream(in);

                // chain the InputStream to a Reader
                Reader r = new InputStreamReader(in);

                int c;
                while ((c = r.read()) != -1) {
                    System.out.write((char) c);
                }

            } catch (MalformedURLException exception) {
                System.err.println(args[0] + " is not a parseable URL");
            } catch (IOException e) {
                System.err.println(e);
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }
}
