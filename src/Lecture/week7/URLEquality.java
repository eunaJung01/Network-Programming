package Lecture.week7;

import java.net.MalformedURLException;
import java.net.URL;

public class URLEquality {

    public static void main(String[] args) {
        try {
            URL www = new URL("http://www.ibiblio.org/");
            URL ibiblio = new URL("http://ibiblio.org/");
            // 같다고 판단

            if (ibiblio.equals(www)) {
                System.out.println("equals(): " + ibiblio + " is the same as " + www);
            } else {
                System.out.println("equals(): " + ibiblio + " is not the same as " + www);
            }

            if (ibiblio.sameFile(www)) {
                System.out.println("sameFile(): " + ibiblio + " is the same as " + www);
            } else {
                System.out.println("sameFile(): " + ibiblio + " is not the same as " + www);
            }

            // ---
            System.out.println();
            // ---

            URL www1 = new URL("http://www.ncsa.uiuc.edu/HTMLPrimer.html#GS");
            URL ibiblio1 = new URL("http://www.ncsa.uiuc.edu/HTMLPrimer.html#HD");
            // fragment를 다르게 줬기 때문에 equals()는 다르다고 판단
            // sameFile()은 같다고 판단

            if (ibiblio1.equals(www1)) {
                System.out.println("equals(): " + ibiblio1 + " is the same as " + www);
            } else {
                System.out.println("equals(): " + ibiblio1 + " is not the same as " + www);
            }

            if (ibiblio1.sameFile(www1)) {
                System.out.println("sameFile(): " + ibiblio1 + " is the same as " + www);
            } else {
                System.out.println("sameFile(): " + ibiblio1 + " is not the same as " + www);
            }

        } catch (MalformedURLException exception) {
            exception.printStackTrace();
        }
    }

}
