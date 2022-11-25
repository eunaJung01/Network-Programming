package Lecture.week10;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class WriteDataToServer {

    public static void main(String[] args) {
        try {
            URL u = new URL("http://www.somehost.com/cgi-bin/acgi");
            // open the connection and prepare for it
            URLConnection uc = u.openConnection();
            uc.setDoInput(true);

            OutputStream raw = uc.getOutputStream();
            OutputStream buffered = new BufferedOutputStream(raw);
            OutputStreamWriter out = new OutputStreamWriter(buffered, "8859_1");

            out.write("first=Julie&middle=&last=Harting&work=String+Quartet\r\n");
            out.flush();
            out.close();

        } catch (IOException exception) {
            System.err.println(exception);
        }
    }
}
