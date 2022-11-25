package Homework.week3;

import java.io.IOException;
import java.io.OutputStream;

public class GenerateCharactersByteArray {

    public static void main(String[] args) {
        try {
            generateCharacters(System.out); // 자식
        } catch (IOException ex) {
        }
    }

    public static void generateCharacters(OutputStream out) throws IOException { // 부모
        int firstPrintableCharacter = 33; // printable ASCII characters start from 33
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerLine = 72;

        int start = firstPrintableCharacter;
        int count = 0;

        // use the method write(byte[] data) to write one line at a time (not a byte at a time)
        byte[] data = new byte[numberOfCharactersPerLine + 2];

        while (count < 1000) {
            for (int i = start; i < start + numberOfCharactersPerLine; i++) {
                data[i - start] = ((byte) ((i + firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter));
            }

            data[numberOfCharactersPerLine] = (byte) '\r'; // carriage return
            data[numberOfCharactersPerLine + 1] = (byte) '\n'; // line feed

            out.write(data);
            start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;

            count++;
        }
    }

}
