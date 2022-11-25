package Homework.week3;

import Lecture.week2.GenerateCharactersSingleByte;

import java.io.IOException;

public class GenerateCharactersHomeworkSpeedComparison {

    public static void main(String[] args) {
        int cnt = 100;
        double avgTimeSingle = 0;
        double avgTimeArray = 0;
        long startTime = 0;
        long endTime = 0;

        try {
            for (int i = 0; i < cnt; i++) {
                startTime = System.currentTimeMillis();
                GenerateCharactersSingleByte.generateCharacters(System.out);
                endTime = System.currentTimeMillis();
                avgTimeSingle += (endTime - startTime);

                startTime = System.currentTimeMillis();
                GenerateCharactersByteArray.generateCharacters(System.out);
                endTime = System.currentTimeMillis();
                avgTimeArray += (endTime - startTime);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("Single Byte = " + avgTimeSingle / cnt + "ms");
        System.out.println("Byte Array = " + avgTimeArray / cnt + "ms");
    }

}
