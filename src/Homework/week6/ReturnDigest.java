package Homework.week6;

import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class ReturnDigest extends Thread {

    private String filename;
    private byte[] digest;

    public ReturnDigest(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);

            while (din.read() != -1) ; // read entire file
            din.close();

            digest = sha.digest();

            System.out.println("run() - " + filename);

        } catch (Exception e) {
            System.err.print(e);
        }
    }

    public byte[] getDigest() {
        return digest;
    }

}
