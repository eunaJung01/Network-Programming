package Lecture.week14;

import java.nio.ByteBuffer;

public class DataConversionTest {

    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocate(16);

        int i = 0;
        while (buf.hasRemaining()) {
            buf.put((byte) i);
            i++;
        }
        buf.flip();

        System.out.println(buf); // java.nio.HeapByteBuffer[pos=0 lim=16 cap=16]
        showBuffer(buf, "int");
        showBuffer(buf, "char");
        showBuffer(buf, "float");
        showBuffer(buf, "long");
    }

    private static void showBuffer(ByteBuffer buf, String type) {
        switch (type) {
            case "int":
                while (buf.hasRemaining()) {
                    System.out.println(buf.getInt());
                }
                buf.flip();
                break;

            case "char":
                while (buf.hasRemaining()) {
                    System.out.println(buf.getChar());
                }
                buf.flip();
                break;

            case "float":
                while (buf.hasRemaining()) {
                    System.out.println(buf.getFloat());
                }
                buf.flip();
                break;

            case "long":
                while (buf.hasRemaining()) {
                    System.out.println(buf.getLong());
                }
                buf.flip();
                break;
        }
    }

}
