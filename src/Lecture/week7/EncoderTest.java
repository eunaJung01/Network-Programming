package Lecture.week7;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncoderTest {

    public static void main(String[] args) {
        try {
            System.out.println(URLEncoder.encode("This string has spaces", "UTF-8"));
            System.out.println(URLEncoder.encode("This*string*has*asterisks", "UTF-8"));
            System.out.println(URLEncoder.encode("This+string+has+pluses", "UTF-8"));
            System.out.println(URLEncoder.encode("This/string/has/slashes", "UTF-8"));
            System.out.println(URLEncoder.encode("This\"string\"has\"quote\"marks", "UTF-8"));
            System.out.println(URLEncoder.encode("This:string:has:colons", "UTF-8"));
            System.out.println(URLEncoder.encode("This~string~has~tildes", "UTF-8"));
            System.out.println(URLEncoder.encode("This(string)has(parentheses)", "UTF-8"));
            System.out.println(URLEncoder.encode("This.string.has.periods", "UTF-8"));
            System.out.println(URLEncoder.encode("This=string=has=equals=signs", "UTF-8"));
            System.out.println(URLEncoder.encode("This&string&has&ampersands", "UTF-8"));
            System.out.println(URLEncoder.encode("Thiséstringéhasé non-ASCII characters", "UTF-8"));

            System.out.println(URLEncoder.encode("http://203.252.148.149/N10 b10.txt", "UTF-8"));
            // → http%3A%2F%2F203.252.148.149%2FN10+b10.txt
            // problem : 모든 것을 다 ASCII로 encoding 해버렸다 ('://'까지 encoding 되면 안됨!)
            // 'N10 b10.txt'에서 공백만 %20으로 바뀌길 원했지만 전부 다 encoding 되어버림

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Broken VM does not support UTF-8");
        }
    }

}
