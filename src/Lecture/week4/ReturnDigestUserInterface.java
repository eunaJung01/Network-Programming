package Lecture.week4;

public class ReturnDigestUserInterface {

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

    // Exception in thread "main" java.lang.NullPointerException
    public static void main(String[] args) {
        for (String filename : args) {
            // Calculate the digest
            ReturnDigest dr = new ReturnDigest(filename);
            dr.start();

            // Now print the result
            System.out.print(filename + " : ");
            byte[] digest = dr.getDigest();
            System.out.print(toHexString(digest));
            System.out.println();
        }
    }

}
