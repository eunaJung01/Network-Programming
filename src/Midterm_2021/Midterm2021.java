//package Midterm_2021;
//
//import java.io.*;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.util.concurrent.*;
//
//public class Midterm2021 {
//
//    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        long timeSum = 0L;
//
//        for (int k = 0; k < 5; k++) {
//            long start = System.currentTimeMillis();
//
//            ExecutorService service = Executors.newFixedThreadPool(50);
//            String baseURL = "https://home.konkuk.ac.kr/~leehw/Site/nptest/2021/MidTerM/";
//
//            Result res = new Result();
//
//            Thread[][] threads = new Thread[61][51];
//            Future<?>[][] futures = new Future[61][51];
//
//            for (int i = 1; i < 61; i++) {
//                for (int j = 1; j < 51; j++) {
//                    threads[i][j] = new MultiThread(i, j, baseURL, res);
//                    futures[i][j] = service.submit(threads[i][j]);
//                }
//            }
//
//            for (int i = 1; i < 61; i++) {
//                for (int j = 1; j < 51; j++) {
//                    futures[i][j].get();
//                    System.out.println(i + ":" + j);
//                }
//            }
//
//            res.printMax();
//            res.printMin();
//            res.printAvg();
//            res.printMissed();
//
//            service.shutdown();
//
//            long end = System.currentTimeMillis();
//            System.out.println("runtime : " + (end - start));
//            timeSum += (end - start);
//        }
//
//        System.out.println("average runtime : " + (timeSum) / 5 / 1000 + "s");
//    }
//
//
//}
//
//class MultiThread extends Thread {
//
//    int c;
//    int d;
//    String baseURl;
//    Result result;
//
//    int row = 1;
//
//    float max = Float.MIN_VALUE;
//
//    int max_row = 0;
//    int max_col = 0;
//
//    float min = Float.MAX_VALUE;
//
//    int min_row = 0;
//    int min_col = 0;
//
//    float sum = 0f;
//    int cnt = 0;
//
//    public MultiThread(int c, int d, String baseURl, Result result) {
//        this.c = c;
//        this.d = d;
//        this.baseURl = baseURl;
//        this.result = result;
//    }
//
//    private String makePathSmall(int c, int d) {
//        return "file%20" +
//                URLEncoder.encode("(c", StandardCharsets.UTF_8) +
//                "=" + c + URLEncoder.encode(")_(d", StandardCharsets.UTF_8) +
//                "=" + d + URLEncoder.encode(").txt", StandardCharsets.UTF_8);
//    }
//
//    private String makePathMiddle(int c, int d) {
//        return "file%20" +
//                URLEncoder.encode("{c", StandardCharsets.UTF_8) +
//                "=" + c + URLEncoder.encode("}_{d", StandardCharsets.UTF_8) +
//                "=" + d + URLEncoder.encode("}.txt", StandardCharsets.UTF_8);
//    }
//
//    @Override
//    public void run() {
//        try {
//            //객체가 새로 들어오지 않으면 전부 실패
//            Object o = null;
//
//            // small path 확인
//            try {
//                String sb = makePathSmall(c, d);
//                URL url = new URL(baseURl + sb);
//                o = url.getContent();
//            } catch (IOException ignored) {
//                //System.out.println("ignored = " + ignored);
//            }
//
//            // middle path 확인
//            if (o == null) {
//                try {
//                    String sb = makePathMiddle(c, d);
//                    URL url = new URL(baseURl + sb);
//                    o = url.getContent();
//                } catch (IOException ignored) {
//                    //System.out.println("ignored = " + ignored);
//                }
//            }
//
//            //객체가 전부 들어왔을 때만 실행
//            //한번에 파일 내부의 값을 전부 계산하여 synchronized 부분이 최대한 조금 불리게끔 했다.
//            if (o != null) {
//                InputStream is = (InputStream) o;
//                InputStreamReader Isr = new InputStreamReader(is);
//                BufferedReader br = new BufferedReader(Isr);
//
//                String line;
//                while ((line = br.readLine()) != null) {
//                    String[] li = line.replace("\n", "").split("\t");
//                    for (int i = 0; i < li.length; i++) {
//                        float num = Float.parseFloat(li[i]);
//                        sum += num;
//                        cnt += 1;
//                        if (num > max) {
//                            max = num;
//                            max_row = row;
//                            max_col = i + 1;
//                        }
//                        if (min > num) {
//                            min = num;
//                            min_row = row;
//                            min_col = i + 1;
//                        }
//                    }
//                    row += 1;
//                }
//
//                result.setMax(max, c, d, max_row, max_col);
//                result.setMin(min, c, d, min_row, min_col);
//                result.addNum(sum, cnt);
//
//                br.close();
//            } else {
//                result.addMissedFile();
//            }
//        } catch (IOException ignored) {
//            //System.out.println("ignored = " + ignored);
//        }
//    }
//}
//
//class Result {
//    float max = Float.MIN_VALUE;
//    int max_c;
//    int max_d;
//    int max_row;
//    int max_col;
//
//    float min = Float.MAX_VALUE;
//    int min_c;
//    int min_d;
//    int min_row;
//    int min_col;
//
//    float sum = 0f;
//    int cnt = 0;
//
//    int missedFile = 0;
//
//    public synchronized void addMissedFile() {
//        this.missedFile += 1;
//    }
//
//    public synchronized void setMax(float num, int c, int d, int row, int col) {
//        if (num > max) {
//            this.max = num;
//            this.max_row = row;
//            this.max_col = col;
//            this.max_c = c;
//            this.max_d = d;
//        }
//    }
//
//    public synchronized void setMin(float num, int c, int d, int row, int col) {
//        if (num < min) {
//            this.min = num;
//            this.min_row = row;
//            this.min_col = col;
//            this.min_c = c;
//            this.min_d = d;
//        }
//    }
//
//    public void printMax() {
//        System.out.println("c:" + max_c + "\td:" + max_d + "\trow:" + max_row + "\tcol:" + max_col + "\tmax:" + max);
//    }
//
//    public void printMin() {
//        System.out.println("c:" + min_c + "\td:" + min_d + "\trow:" + min_row + "\tcol:" + min_col + "\tmin:" + min);
//    }
//
//    public void printAvg() {
//        System.out.printf("avg : %.4f%n", sum / (float) cnt);
//    }
//
//    public synchronized void addNum(float num, int cnt) {
//        sum += num;
//        this.cnt += cnt;
//    }
//
//    public void printMissed() {
//        System.out.println("number of missing files : " + missedFile);
//    }
//}
