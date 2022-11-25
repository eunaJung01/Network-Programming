package Lecture.week4;

import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class InstanceCallbackDigest implements Runnable {

    private String filename;
    private InstanceCallbackDigestUserInterface callback;

    public InstanceCallbackDigest(String filename, InstanceCallbackDigestUserInterface callback) {
        this.filename = filename;
        this.callback = callback; // callback instance stored in a field
    }

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);

            while (din.read() != -1) ; // read entire file
            din.close();

            byte[] digest = sha.digest();
            callback.receiveDigest(digest); // 출력

        } catch (Exception e) {
            System.err.print(e);
        }
    }

}
