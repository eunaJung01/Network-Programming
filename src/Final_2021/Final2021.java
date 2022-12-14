package Final_2021;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Final2021 {

    public final static String IP_ADDRESS = "203.252.148.148";
    public final static int PORT = 2021;
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

            // 한글 문자열
            byte[] res_kor = new byte[40];
            byteBuffer.get(res_kor);
            String pattern = new String(res_kor, "EUC-kr");
            System.out.println(pattern);
            /*
            byte[] request = new byte[120];
            정은아님, float 2개, int 2개 반복입니다.=��<�c�  ��  (?9o�<��   �&  �=+�?�K  ��  )
            ?txF>�1|  L�  U�>�b=� x  e  C^
            -> byte array에서 int, float 값 빼오는 것은 절망임
             */

            for (int i = 0; i < 20; i += 4) { // 숫자 20개
                // float 2개
                for (int j = 0; j < 2; j++) {
                    float res_float = byteBuffer.getFloat();
                    System.out.println("res_float = " + res_float);
                    if (float_max < res_float) {
                        float_max = res_float;
                    }
                }
                // int 2개
                for (int j = 0; j < 2; j++) {
                    int res_int = byteBuffer.getInt();
                    System.out.println("res_int = " + res_int);
                    if (int_max < res_int) {
                        int_max = res_int;
                    }
                }
            }

            // result
            System.out.println();
            System.out.println("결과");
            System.out.println("int_max = " + int_max); // 45862
            System.out.println("float_max = " + float_max); // 0.9549602

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
