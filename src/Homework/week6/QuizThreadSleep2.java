package Homework.week6;

// sleep sort
//*
public class QuizThreadSleep2 {

    static class Thread extends java.lang.Thread {
        int num = 0;

        public Thread(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            try {
                sleep(num * 100);
                System.out.println(num);
            } catch (Exception e) {
            }
        }
    }


    public static void main(String[] args) {
        int arr_length = 10;
        int[] arr = new int[arr_length];

        for (int i = 0; i < arr_length; i++) {
            arr[i] = (int) (Math.random() * 10);
        }

        for (int i = 0; i < arr_length; i++) {
            Thread t = new Thread(arr[i]);
            t.start();
        }
    }

}
//*/

// 교수님 코드
/*
public class QuizThreadSleep2 implements Runnable {

    private int num;

    public QuizThreadSleep2(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(num * 50);
            System.out.println(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int[] nums = {7, 3, 2, 1, 0, 5};

        for (int num : nums) {
            QuizThreadSleep2 qt = new QuizThreadSleep2(num);
            Thread th = new Thread(qt);
            th.start();
        }
    }

}
//*/
