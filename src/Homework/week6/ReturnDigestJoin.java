package Homework.week6;

public class ReturnDigestJoin {

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

    // Polling : Proceed if returned value is valid
    public static void main(String[] args) throws InterruptedException {
        ReturnDigest[] digestThreads = new ReturnDigest[args.length];

        for (int i = 0; i < args.length; i++) {
            // Calculate the digest
            digestThreads[i] = new ReturnDigest(args[i]);
            digestThreads[i].start();
        }

        for (int i = 0; i < args.length; i++) {
            // Now join to the threads
            digestThreads[i].join();

            // Now print the result
            StringBuffer result = new StringBuffer(args[i]);
            result.append(" : ");

            byte[] digest = digestThreads[i].getDigest();
            result.append(toHexString(digest));
            System.out.println(result); // print result only if digest is not null
        }
    }

}
