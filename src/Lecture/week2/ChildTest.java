package Lecture.week2;

import java.io.FileOutputStream;
import java.io.IOException;

class Parent2 {
    int i = 7;

    public int get() {
        return i;
    }
}

class Child2 extends Parent2 {
    int i = 5;

    public int get() {
        return i;
    }
}

public class ChildTest {

    public static void print(Parent2 parent2) {
        System.out.println(parent2.i); // field in superclass
        System.out.println(parent2.get()); // method in subclass
    }

    public static void main(String[] args) {
        Parent2 p = new Parent2();
        System.out.println("------ 1 ------");
        System.out.println(p.i); // 7
        System.out.println(p.get()); // 7

        Child2 c = new Child2();
        System.out.println("------ 2 ------");
        System.out.println(c.i); // 5
        System.out.println(c.get()); // 5

        Parent2 p2 = new Child2(); // Child2 instantiated and referenced by Parent2
        System.out.println("------ 3 ------");
        System.out.println(p2.i); // 7
        System.out.println(p2.get()); // 5

        System.out.println("------ 4 ------");
        print(c); // 7, 5
        print(p2); // 7, 5
    }

}
