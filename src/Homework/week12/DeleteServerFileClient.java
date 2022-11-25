package Homework.week12;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

// TODO: Write a client that requests DELETE of a file on server (as described in the lecture)
public class DeleteServerFileClient {

    public static InputStream inputStream;
    public static InputStream buffer;
    public static BufferedReader bufferedReader;

    public static void main(String[] args) throws IOException {
        try {
            // TODO: open connection
            URL u = new URL("http://localhost");
            URLConnection uc = u.openConnection();
            HttpURLConnection http = (HttpURLConnection) uc;
            http.setRequestMethod("DELETE");
            http.connect();

            // TODO: print http response code
            String responseCode = String.valueOf(http.getResponseCode());
            System.out.println("http responseCode: " + responseCode);

            // TODO: print content
            inputStream = http.getInputStream();
            buffer = new BufferedInputStream(inputStream);
            bufferedReader = new BufferedReader(new InputStreamReader(buffer));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException exception) {
            exception.printStackTrace();

        } finally {
            inputStream.close();
            buffer.close();
            bufferedReader.close();
        }
    }

}
