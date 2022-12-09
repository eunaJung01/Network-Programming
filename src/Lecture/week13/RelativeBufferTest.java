package Lecture.week13;

import java.nio.ByteBuffer;

public class RelativeBufferTest {

    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocate(10);
        System.out.print("Init Position: " + buf.position()); // 0
        System.out.print(", Init Limit: " + buf.limit()); // 10
        System.out.println(", Init Capacity: " + buf.capacity()); // 10

        buf.mark(); // mark = 0
        buf.put((byte) 10).put((byte) 11).put((byte) 12);
        buf.reset(); // move position to mark

        System.out.println("Value: " + buf.get() + ", Position: " + buf.position()); // Value: 10, Position: 1
        System.out.println("Value: " + buf.get() + ", Position: " + buf.position()); // Value: 11, Position: 2
        System.out.println("Value: " + buf.get() + ", Position: " + buf.position()); // Value: 12, Position: 3
        System.out.println("Value: " + buf.get() + ", Position: " + buf.position()); // Value: 0, Position: 4
    }

}
