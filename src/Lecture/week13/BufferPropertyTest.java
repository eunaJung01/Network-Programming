package Lecture.week13;

import java.nio.ByteBuffer;

public class BufferPropertyTest {

    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocate(10);
        System.out.println("initial position: " + buf.position()); // 0
        System.out.println("initial limit: " + buf.limit()); // 10
        System.out.println("capacity: " + buf.capacity()); // 10
        System.out.println(buf); // java.nio.HeapByteBuffer[pos=0 lim=10 cap=10]
        // mark: 정의되지 않았음 -> 호출해야 함

        buf.position(4);
        buf.mark(); // mark == 4 (현재 position)
        System.out.println(buf); // java.nio.HeapByteBuffer[pos=4 lim=10 cap=10]

        buf.position(6);
        System.out.println(buf); // java.nio.HeapByteBuffer[pos=6 lim=10 cap=10]

        buf.reset(); // current position is set to marked position
        // mark 호출하지 않고 reset을 호출한다면 -> InvalidMarkException
        System.out.println(buf); // java.nio.HeapByteBuffer[pos=4 lim=10 cap=10]

        // ---------------------------------------------------------------------------------

        System.out.println("\nDirect Allocation");
        ByteBuffer buffer = ByteBuffer.allocateDirect(12);

        buffer.put((byte) 10).put((byte) 11).put((byte) 12).put((byte) 13).put((byte) 14).put((byte) 15);
        buffer.mark();
        System.out.println(buffer); // java.nio.DirectByteBuffer[pos=6 lim=12 cap=12]

        buffer.put((byte) 10).put((byte) 11).put((byte) 12).put((byte) 13).put((byte) 14).put((byte) 15);
        buffer.reset();
        buffer.limit(11);
        System.out.println(buffer); // java.nio.DirectByteBuffer[pos=6 lim=11 cap=12]
    }

}
