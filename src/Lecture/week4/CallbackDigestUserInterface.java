package Lecture.week4;

public class CallbackDigestUserInterface {

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

    public static void receiveDigest(byte[] digest, String name) throws IncompatibleClassChangeError {
        StringBuilder result = new StringBuilder(name);
        result.append(" : ");
        result.append(toHexString(digest));
        System.out.println(result);
    }

    // main does nothing to print (직접적으로 결과를 출력하지 않음, run 안에서 이루어짐)
    public static void main(String[] args) throws InterruptedException {
        for (String filename : args) {
            // Calculate the digest
            CallbackDigest cb = new CallbackDigest(filename);
            Thread t = new Thread(cb);
            t.start();
        }
    }

}