package Lecture.week2;

public class OutputStreamWriteTest {

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            System.out.write(i); // read LSB
            System.out.print(i); // print ASCII of the byte value
            System.out.flush();
            System.out.println();
        }
    }

}
