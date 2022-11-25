package Homework.week3;

import Lecture.week3.FileView;

public class FileViewSpeedComparison {

    public static void main(String[] args) {
        int cnt = 100;
        double avgTime1 = 0;
        double avgTime2 = 0;
        long startTime = 0;
        long endTime = 0;

        for (int i = 0; i < cnt; i++) {
            startTime = System.currentTimeMillis();
            FileView.fileView();
            endTime = System.currentTimeMillis();
            avgTime1 += (endTime - startTime);

            startTime = System.currentTimeMillis();
            FileView2.fileView2();
            endTime = System.currentTimeMillis();
            avgTime2 += (endTime - startTime);
        }

        System.out.println();
        System.out.println("FileView1 = " + avgTime1 / cnt + "ms");
        System.out.println("FileView2 = " + avgTime2 / cnt + "ms");
    }

}
