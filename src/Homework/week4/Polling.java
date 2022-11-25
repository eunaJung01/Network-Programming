package Homework.week4;

import Lecture.week4.ReturnDigest;

public class Polling {

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
    /*
    public static void main(String[] args) throws InterruptedException {
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

            if (digest != null) {
                result.append(toHexString(digest));
                System.out.println(result); // print result only if digest is not null
            }
        }
    }
    //*/

    // Polling : Proceed if returned value is valid
    // CPU 독점 → 아무것도 출력되지 않는 채로 무한 루프 걸림
    // sleep 추가하면 정상 출력
    //*
    public static void main(String[] args) throws InterruptedException {
        ReturnDigest[] digests = new ReturnDigest[args.length];

        for (int i = 0; i < args.length; i++) {
            // Calculate the digest
            digests[i] = new ReturnDigest(args[i]);
            digests[i].start();
        }

        for (int i = 0; i < args.length; i++) {
            // Now print the result
            while (true) {
                Thread.sleep(10);
                byte[] digest = digests[i].getDigest();

                if (digest != null) {
                    StringBuffer result = new StringBuffer(args[i]);
                    result.append(" : ");
                    result.append(toHexString(digest));
                    System.out.println(result); // print result only if digest is not null
                    break;
                }
            }
        }
    }
    //*/

}
