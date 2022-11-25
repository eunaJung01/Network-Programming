package Lecture.week9;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class BinarySaver {

    public static void main(String[] args) {
        try {
            URL root = new URL("http://www.lolcats.com/images/logo.png");
            saveBinaryFile(root);

        } catch (IOException exception) {
            System.err.println(exception);
        }
    }

    public static void saveBinaryFile(URL u) throws IOException {
        URLConnection uc = u.openConnection();

        String contentType = uc.getContentType();
        int contentLength = uc.getContentLength();

        if (contentType.startsWith("text/") || contentLength == -1) {
            throw new IOException("This is not a binary file.");
        }

        try (InputStream raw = uc.getInputStream()) {
            InputStream in = new BufferedInputStream(raw);
            byte[] data = new byte[contentLength];

            int offset = 0;
            while (offset < contentLength) {
                int bytesRead = in.read(data, offset, data.length - offset);
                offset += bytesRead;
            }
            if (offset != contentLength) {
                throw new IOException("Only read " + offset + " bytes; Expected " + contentLength + " bytes");
            }

            String filename = u.getFile(); // /images/logo.png
            filename = filename.substring(filename.lastIndexOf('/') + 1); // find substring & update filename
            try (FileOutputStream fout = new FileOutputStream(filename)) {
                fout.write(data);
                fout.flush();
            }
        }
    }

}
