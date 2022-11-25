package Lecture.week4;

public class InstanceCallbackDigestUserInterface {

    private String filename;
    private byte[] digest;

    public InstanceCallbackDigestUserInterface(String filename) {
        this.filename = filename;
    }

    public void calculateDigest() {
        InstanceCallbackDigest cb = new InstanceCallbackDigest(filename, this);
        Thread t = new Thread(cb);
        t.start();
    }

    void receiveDigest(byte[] digest) { // 출력
        this.digest = digest;
        System.out.println(this); // toString
    }

    @Override
    public String toString() {
        String result = filename + " : ";
        if (digest != null) {
            result += toHexString(digest);
        } else {
            result += "digest not available";
        }
        return result;
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
            // Calculate the digest
            InstanceCallbackDigestUserInterface d = new InstanceCallbackDigestUserInterface(filename);
            d.calculateDigest();
        }
    }

}
