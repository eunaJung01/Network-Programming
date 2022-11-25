package Lecture.week4;

public class FirstTry_FixRaceCondition {

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

    // First Try
    // But still.. -> Exception in thread "main" java.lang.NullPointerException
    public static void main(String[] args) {
        ReturnDigest[] digests = new ReturnDigest[args.length];

        for (int i = 0; i < args.length; i++) {
            // Calculate the digest
            digests[i] = new ReturnDigest(args[i]);
            digests[i].start();
        }

        for (int i = 0; i < args.length; i++) {
            // Now print the result
            StringBuffer result = new StringBuffer(args[i]);
            result.append(" : ");
            byte[] digest = digests[i].getDigest();
            result.append(toHexString(digest));

            System.out.println(result);
        }
    }

}
