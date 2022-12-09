package Lecture.week13;

import java.nio.ByteBuffer;

public class BulkWriteTest {

    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocate(10);
        buf.position(5);
        buf.mark();
        System.out.println("Position: " + buf.position() + ", Limit: " + buf.limit()); // Position: 5, Limit: 10

        byte[] b = new byte[15];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) i;
        }

        int size = buf.remaining();
        if (b.length < size) {
            size = b.length;
        }
        System.out.println("Position: " + buf.position() + ", Limit: " + buf.limit()); // Position: 5, Limit: 10

        buf.put(b, 0, size); // b의 0에서부터 size만큼 b에 있는 내용을 써라
        System.out.println("Position: " + buf.position() + ", Limit: " + buf.limit()); // Position: 10, Limit: 10

        doSomething(buf, size);
    }

    private static void doSomething(ByteBuffer buf, int size) {
        buf.reset(); // solution of BufferUnderflowException
        for (int i = 0; i < size; i++) {
            System.out.println("byte = " + buf.get());
        }
    }

}
