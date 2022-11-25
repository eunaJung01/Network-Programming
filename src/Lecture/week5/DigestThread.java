package Lecture.week5;

import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class DigestThread extends Thread {

    private String filename;

    public DigestThread(String filename) {
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

            byte[] digest = sha.digest(); // digest : file 내용에 기반한 hash 값을 저장해놓는 곳

            /* bad
            // DigestThread with 'System.out.println()' instead of using variable 'result'
            System.out.print(filename + " : ");
            System.out.print(toHexString(digest));
            System.out.println();
            //*/

            /* good
            StringBuilder result = new StringBuilder(filename);
            result.append(" : ");
            result.append(toHexString(digest));
            System.out.println(result);
             //*/

            //* synchronization
            synchronized (System.out) {
                System.out.print(filename + " : ");
                System.out.print(toHexString(digest));
                System.out.println();
            }
            //*/

            /* synchronization with sleeping
            synchronized (System.out) {
                System.out.println(filename + " : ");
                if (filename.matches("./data.bin")) {
                    Thread.yield();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println(filename + " : " + e);
                    }
                }
                System.out.println(toHexString(digest));
                System.out.println();
            }
            //*/

        } catch (Exception e) {
            System.err.print(e);
        }
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);

            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        for (String filename : args) {
            Thread t = new DigestThread(filename);
            t.start();
        }
    }

}
