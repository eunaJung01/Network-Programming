package Homework.week4;

import java.util.concurrent.ExecutionException;

public class ThreadSpeedComparison {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int arr_length = 10000000;
        int[] arr = new int[arr_length];

        int cnt = 100;
        double avgTime1 = 0;
        double avgTime2 = 0;
        long startTime = 0;
        long endTime = 0;

        for (int i = 0; i < cnt; i++) {
            for (int j = 0; j < arr_length; j++) {
                arr[j] = (int) (Math.random() * 1000000000);
            }

            // single thread
            startTime = System.currentTimeMillis();
            FindMaxTask single = new FindMaxTask(arr, 0, arr_length);
            System.out.println(single.call());
            endTime = System.currentTimeMillis();
            avgTime1 += (endTime - startTime);

            // FindMaxTask + MultithreadedMaxFinder
            startTime = System.currentTimeMillis();
            System.out.println(MultithreadedMaxFinder.max(arr));
            endTime = System.currentTimeMillis();
            avgTime2 += (endTime - startTime);

            System.out.println("---");
        }

        System.out.println();
        System.out.println("single thread = " + avgTime1 / cnt + "ms");
        System.out.println("FindMaxTask + MultithreadedMaxFinder = " + avgTime2 / cnt + "ms");

        /*
        single thread = 2.32ms
        FindMaxTask + MultithreadedMaxFinder = 2.06ms
         */
    }

}
