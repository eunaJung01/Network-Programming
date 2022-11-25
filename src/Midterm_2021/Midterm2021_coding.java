package Midterm_2021;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

public class Midterm2021_coding {

    private final static int THREAD_COUNT = 15;
    private final static int c = 61; // 61
    private final static int d = 51; // 51
    private final static int n = 1; // 5

    static int missing_files = 0;

    static int c_max = 0;
    static int c_min = 0;
    static int d_max = 0;
    static int d_min = 0;

    static int row_max = 0;
    static int row_min = 0;
    static int col_max = 0;
    static int col_min = 0;

    static float max = 0; // 최댓값
    static float min = 0; // 최솟값
    static float num = 0; // 전체 개수
    static float total = 0; // 전체 합

    static synchronized void is_missing_file() {
        missing_files++;
    }

    static synchronized void compare(float[] result) {
        /// float[]{c, d, row_max, col_max, row_min, col_min, max, min, num, total}

        // max
        if (max < result[6]) {
            c_max = (int) result[0];
            d_max = (int) result[1];
            row_max = (int) result[2];
            col_max = (int) result[3];
            max = result[6];
        }
        // min
        if (min > result[7]) {
            c_min = (int) result[0];
            d_min = (int) result[1];
            row_min = (int) result[4];
            col_min = (int) result[5];
            min = result[7];
        }
        num += result[8]; // 전체 개수
        total += result[9]; // 전체 합
    }

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        String address = "https://home.konkuk.ac.kr/~leehw/Site/nptest/2021/MidTerM/file%20";

        long total_time = 0L;
        for (int k = 0; k < n; k++) {
            ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

            long start = System.currentTimeMillis();

            Thread[][] threads = new Thread[c][d];
            Future<?>[][] futures = new Future[c][d];

            for (int i = 1; i < c; i++) { // c : 1 ~ 60
                for (int j = 1; j < d; j++) { // d : 1 ~ 50
                    threads[i][j] = new Thread(i, j, address, URLEncoder.encode("(c=" + i + ")_(d=" + j + ").txt", String.valueOf(StandardCharsets.UTF_8)), URLEncoder.encode("{c=" + i + "}_{d=" + j + "}.txt", String.valueOf(StandardCharsets.UTF_8)));
                    futures[i][j] = executor.submit(threads[i][j]);
                }
            }
            executor.shutdown();

            for (int i = 1; i < c; i++) { // c : 1 ~ 60
                for (int j = 1; j < d; j++) { // d : 1 ~ 50
                    futures[i][j].get();
                }
            }
            long end = System.currentTimeMillis();
            total_time += end - start;

            // initialize
            if (k != (n - 1)) {
                num = 0;
                total = 0;
                missing_files = 0;
            }
        }
        System.out.println();
        System.out.println("c: " + c_max + "\t\td: " + d_max + "\t\trow: " + row_max + "\t\tcolumn: " + col_max + "\t\tmax: " + max);
        System.out.println("c: " + c_min + "\t\td: " + d_min + "\t\trow: " + row_min + "\t\tcolumn: " + col_min + "\t\tmin: " + min);
        System.out.printf("avg : %.4f%n", total / num);
        System.out.println("number of missing files : " + missing_files);

        System.out.printf("Run-time : %.3fs%n", (float) total_time / 1000);
        System.out.printf("average runtime : %.3f%n", (float) total_time / (n * 1000));
    }

}

class Thread implements Runnable {

    int c;
    int d;
    String[] urls;

    int row_max = 0;
    int row_min = 0;
    int col_max = 0;
    int col_min = 0;

    float max = 0; // 최댓값
    float min = 0; // 최솟값
    int num = 0; // 전체 숫자 개수
    float total = 0; // 전체 합


    // Constructor
    public Thread(int c, int d, String address, String fileName1, String fileName2) {
        this.c = c;
        this.d = d;
        this.urls = new String[]{address + fileName1, address + fileName2};
    }

    // min, max, total 변경
    public void fixValue(float f, int row, int col) {
        if (f > max) { // 최댓값
            max = f;
            row_max = row;
            col_max = col;
        }
        if (f < min) { // 최솟값
            min = f;
            row_min = row;
            col_min = col;
        }
        total += f; // 전체 합
    }

    @Override
    public void run() {
        System.out.println(c + ":" + d);
        int cnt = 0;
        for (String url : urls) {
            try {
                URL u = new URL(url);
                BufferedReader br = null;
                try (InputStream in = new BufferedInputStream(u.openStream())) {
                    InputStreamReader file = new InputStreamReader(in);
                    br = new BufferedReader(file);
                    String l;
                    int row = 0;

                    while ((l = br.readLine()) != null) {
                        int col = 0;
                        String[] line = l.replace("\n", "").split("\t");
                        row++;
                        for (String val : line) {
                            num++;
                            fixValue(Float.parseFloat(val), row, ++col);
                        }
                    }
                    Midterm2021_coding.compare(new float[]{c, d, row_max, col_max, row_min, col_min, max, min, num, total});

                } catch (FileNotFoundException e) {
                    cnt++;

                } finally {
                    if (br != null) {
                        br.close();
                    }
                }

            } catch (IOException exception) {
                exception.printStackTrace();
            }

            if (cnt == 2) {
                Midterm2021_coding.is_missing_file();
            }
        }
    }

}


/*
Run-time : 126982 milliseconds

c: 51		d: 40		row: 92		column: 371		max: 557.56
c: 6		d: 35		row: 60		column: 826		min: -547.14
avg : 0.9817
number of missing files : 2093

Run-time : 50318 milliseconds

c: 51		d: 40		row: 92		column: 371		max: 557.56
c: 6		d: 35		row: 60		column: 826		min: -547.14
avg : 0.9817
number of missing files : 2093
*/
