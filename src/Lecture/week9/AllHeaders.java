package Lecture.week9;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class AllHeaders {

    public static void main(String[] args) {
        try {
            URL u = new URL("http://www.oreilly.com");
            URLConnection uc = u.openConnection();

            // add a for loop that retrieves all header fields and prints
            Map<String, List<String>> headerFields = uc.getHeaderFields();
            for (String headerField: headerFields.keySet()) {
                if (headerField != null) {
                    System.out.println(headerField + ": " + uc.getHeaderField(headerField));
                }
            }

        } catch (MalformedURLException ex) {
            System.out.println(args[0] + " is not a URL I understand");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        System.out.println();
    }

}
