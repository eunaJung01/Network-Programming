package Lecture.week11;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class DictClient3 {

    public static void main(String[] args) {
        String host = "dict.org";

        try {
            Socket soc = new Socket(host, 2628);

            OutputStream out = soc.getOutputStream();
            Writer writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
            writer = new BufferedWriter(writer);

            InputStream in = soc.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String request = "DEFINE fd-eng-lat gold"; // syntax: DEFINE DB query
//            String request = "DEFINE fd-eng-deu gold";

            writer.write(request);
            writer.flush();
            soc.shutdownOutput();

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                System.out.println(line);
            }

        } catch (UnknownHostException exception) {
            System.out.println("Cannot found the host at " + host);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
