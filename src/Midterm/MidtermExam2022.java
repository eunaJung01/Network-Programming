package Midterm;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MidtermExam2022 {

    private final static int THREAD_COUNT = 15;
    private final static int c = 51; // 51
    private final static int d = 51; // 51
    private final static int n = 5; // 5

    static int missing_files = 0;

    static int max = 0; // 최댓값
    static int min = 0; // 최솟값

    // frequency
    static int max_frequency_key = 0;
    static int min_frequency_key = 0;
    static int max_frequency_val = 0;
    static int min_frequency_val = 0;

    // 빈도수
    static HashMap<Integer, Integer> total_freq_map = new HashMap<>();

    static synchronized void is_missing_file() {
        missing_files++;
    }

    static synchronized void compare(int[] result, HashMap<Integer, Integer> freq_map) { // result : [max, min]
        // max
        if (max < result[0]) {
            max = result[0];
        }
        // min
        if (min > result[1]) {
            min = result[1];
        }
        // total_freq_map
        Iterator<Map.Entry<Integer, Integer>> entries = freq_map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, Integer> entry = entries.next();
            if (total_freq_map.containsKey(entry.getKey())) {
                total_freq_map.put(entry.getKey(), freq_map.get(entry.getKey()) + entry.getValue());
            } else {
                total_freq_map.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        String address = "https://home.konkuk.ac.kr/~leehw/Site/nptest/2022/midtermOCT18/file%20";

        long total_time = 0L; // 전체 시간
        for (int k = 0; k < n; k++) {
            long start = System.currentTimeMillis();

            ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
            Thread[][] threads = new Thread[c][d];
            Future<?>[][] futures = new Future[c][d];

            // execute Threads
            for (int i = 1; i < c; i++) { // c : 1 ~ 50
                for (int j = 1; j < d; j++) { // d : 1 ~ 50
                    threads[i][j] = new Thread(i, j, address,
                            URLEncoder.encode("(c=" + i + ")_(d=" + j + ").txt", String.valueOf(StandardCharsets.UTF_8)),
                            URLEncoder.encode("(c=" + i + ")_<d=" + j + ">.txt", String.valueOf(StandardCharsets.UTF_8)),
                            URLEncoder.encode("<c=" + i + ">_(d=" + j + ").txt", String.valueOf(StandardCharsets.UTF_8)),
                            URLEncoder.encode("<c=" + i + ">_<d=" + j + ">.txt", String.valueOf(StandardCharsets.UTF_8)));
                    futures[i][j] = executor.submit(threads[i][j]);
                }
            }
            executor.shutdown();

            // wait until every Thread is done
            for (int i = 1; i < c; i++) { // c : 1 ~ 50
                for (int j = 1; j < d; j++) { // d : 1 ~ 50
                    futures[i][j].get();
                }
            }

            // get values of Map
            ArrayList<Integer> list = new ArrayList<>(); // value list
            for (Map.Entry<Integer, Integer> entry : total_freq_map.entrySet()) {
                list.add(entry.getValue());
            }
            // sort list - default : ACS
            Collections.sort(list);

            max_frequency_val = list.get(list.size() - 1);
            min_frequency_val = list.get(0);

            // get key by min/max value
            for (Map.Entry<Integer, Integer> entry : total_freq_map.entrySet()) {
                if (entry.getValue().equals(max_frequency_val)) { // max
                    max_frequency_key = entry.getKey();
                }
                if (entry.getValue().equals(min_frequency_val)) { // min
                    min_frequency_key = entry.getKey();
                }
            }

            long end = System.currentTimeMillis();
            total_time += end - start;

            System.out.println();
            System.out.println("max_freq_num : " + max_frequency_key + "\t\tmax_freq : " + max_frequency_val);
            System.out.println("min_freq_num : " + min_frequency_key + "\t\tmin_freq : " + min_frequency_val);
            System.out.println("max : " + max + "\t\tmin : " + min);
            System.out.println("number of missing files : " + missing_files);

            // initialize
            missing_files = 0;
            total_freq_map = new HashMap<>();
            max_frequency_key = 0;
            min_frequency_key = 0;
            max_frequency_val = 0;
            min_frequency_val = 0;
            max = 0;
            min = 0;
        }
        System.out.printf("average runtime : %.3fs%n", (float) total_time / (n * 1000));
    }

}

class Thread implements Runnable {

    int c;
    int d;
    String[] urls;

    static HashMap<Integer, Integer> freq_map = new HashMap<>();

    int max = 0; // 최댓값
    int min = 0; // 최솟값

    // Constructor
    public Thread(int c, int d, String address, String fileName1, String fileName2, String fileName3, String fileName4) {
        this.c = c;
        this.d = d;
        this.urls = new String[]{address + fileName1, address + fileName2, address + fileName3, address + fileName4};
    }

    // min, max 변경
    public void fixValue(int f, int row, int col) {
        if (f > max) { // 최댓값
            max = f;
        }
        if (f < min) { // 최솟값
            min = f;
        }
    }

    @Override
    public void run() {
//        System.out.println(c + ":" + d);
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
                        String[] line = l.replace("\n", "").split(" ");
                        row++;
                        for (String val : line) {
                            fixValue(Integer.parseInt(val), row, ++col);
                            if (freq_map.containsKey(Integer.parseInt(val))) {
                                freq_map.put(Integer.parseInt(val), freq_map.get(Integer.parseInt(val)) + 1);
                            } else {
                                freq_map.put(Integer.parseInt(val), 1);
                            }
                        }
                    }
                    MidtermExam2022.compare(new int[]{max, min}, freq_map);

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

            if (cnt == 4) {
                MidtermExam2022.is_missing_file();
            }
        }
    }

}

/*
max_freq_num : 495		max_freq : 46696
min_freq_num : 1579		min_freq : 44778
max : 1997		min : 0
number of missing files : 1727
average runtime : 300.257
*/
