package Lecture.week4;

import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class DigestRunnable implements Runnable {

    private String filename;

    public DigestRunnable(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);

            while (din.read() != -1) ;
            din.close();

            byte[] digest = sha.digest();

            /*/
            System.out.print(filename + " : ");
            System.out.print(toHexString(digest)); // TODO: 위 filename을 출력하는 것보다 상대적으로 느림 -> 뒤늦게 출력됨
            System.out.println();
            //*/

            //*/ good
            StringBuilder result = new StringBuilder(filename);
            result.append(" : ");
            result.append(toHexString(digest));
            System.out.println(result);
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
            DigestRunnable dr = new DigestRunnable(filename);
            Thread t = new Thread(dr);
            t.start();
        }
    }

}
