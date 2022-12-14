package Lecture.week7;

import java.net.URI;
import java.net.URISyntaxException;

public class URISplitter {

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            try {
                URI u = new URI(args[i]);
                System.out.println("The URI is " + u);

                if (u.isOpaque()) { // pass나 depth 등등 없이 flat한 구조인가?
                    System.out.println("This is an opaque URI.");
                    System.out.println("The scheme is " + u.getScheme());
                    System.out.println("The scheme specific part is " + u.getSchemeSpecificPart());
                    System.out.println("The fragment ID is " + u.getFragment());

                } else {
                    System.out.println("This is a hierarchical URI.");
                    System.out.println("The scheme is " + u.getScheme());
                    System.out.println();

                    try {
                        u = u.parseServerAuthority(); // return URI
                        System.out.println("The host is " + u.getHost());
                        System.out.println("The user info is " + u.getUserInfo());
                        System.out.println("The port is " + u.getPort());

                    } catch (URISyntaxException ex) {
                        System.err.println(args[i] + " does not seem to be a URI.");

                        // Must be a registry based authority
                        System.out.println("The authority is " + u.getAuthority());
                    }
                    System.out.println();
                    System.out.println("The path is " + u.getPath());
                    System.out.println("The query string is " + u.getQuery());
                    System.out.println("The fragment ID is " + u.getFragment());
                }

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

}
