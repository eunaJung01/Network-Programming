package Lecture.week4;

import java.io.*;

public class DataOutputStreamTest {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        fos = new FileOutputStream("data.bin");
        dos = new DataOutputStream(fos);

        dos.writeBoolean(false);
        dos.writeByte((byte) 125);
        dos.writeInt(10);
        dos.writeDouble(200.5);

        dos.writeUTF("hello world");

        System.out.println("저장하였습니다");

        fos.close();
        dos.close();

        // ---

        FileInputStream fis = null;
        DataInputStream dis = null;

        fis = new FileInputStream("data.bin");
        dis = new DataInputStream(fis);

        boolean boolVar = dis.readBoolean();
        byte byteVar = dis.readByte();
        int intVar = dis.readInt();
        double doubleVar = dis.readDouble();
        String stringVar = dis.readUTF();

        System.out.println(boolVar);
        System.out.println(byteVar);
        System.out.println(intVar);
        System.out.println(doubleVar);
        System.out.println(stringVar);

        fis.close();
        dis.close();
    }

}
