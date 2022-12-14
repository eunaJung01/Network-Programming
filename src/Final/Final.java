package Final;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Final {

    public final static String IP_ADDRESS = "203.252.148.148";
    public final static int PORT = 2022;
    public final static int MAX_PACKET_SIZE = 65507;
    public final static String STUDENT_ID = "202011365";

    static int int_max = Integer.MIN_VALUE;
    static float float_max = Float.MIN_VALUE;

    public static void main(String[] args) {
        try {
            SocketAddress address = new InetSocketAddress(IP_ADDRESS, PORT);
            SocketChannel socketChannel = SocketChannel.open(address);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);

            // 학번
            char[] studentID = STUDENT_ID.toCharArray();
            for (char number : studentID) {
                byteBuffer.putChar(number);
            }
            byteBuffer.flip();

            // req
            socketChannel.write(byteBuffer);
            byteBuffer.clear();

            // res
            socketChannel.read(byteBuffer);
            byteBuffer.flip();

//            byte[] res_kor = new byte[130];
//            byteBuffer.get(res_kor);
//            String kor = new String(res_kor, "EUC-kr");
//            System.out.println(kor);
//            // 202011365 정은아님,>��  [?3�7  �e?@  V=N�  �^>.�|  閭>��  �?\0$  v�?.�  ��>�	�  �C>�z  � float 1개, int 1개 반복입니다.

            // 한1
            byte[] res_kor1 = new byte[19];
            byteBuffer.get(res_kor1);
            String kor1 = new String(res_kor1, "EUC-kr");
//            System.out.println(kor1);  // 202011365 정은아님,

            for (int i = 0; i < 10; i++) { // 숫자 10개씩
                // float 1개
                float res_float = byteBuffer.getFloat();
//                System.out.println("res_float = " + res_float);
                if (float_max < res_float) {
                    float_max = res_float;
                }
                // int 1개
                int res_int = byteBuffer.getInt();
//                System.out.println("res_int = " + res_int);
                if (int_max < res_int) {
                    int_max = res_int;
                }
            }

            // 한2 (31 bytes)
            byte[] res_kor2 = new byte[31];
            byteBuffer.get(res_kor2);
            String kor2 = new String(res_kor2, "EUC-kr");
//            System.out.println(kor2);  // float 1개, int 1개 반복입니다.

            // result
            System.out.println(kor1 + kor2); // 202011365 정은아님, float 1개, int 1개 반복입니다.
            System.out.println("int_max = " + int_max); // 54767
            System.out.println("float_max = " + float_max); // 0.86010957

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
