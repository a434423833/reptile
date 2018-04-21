package thread;

import java.util.Calendar;
import java.util.Random;

/**
 * 测评多线程时间
 *
 * @author caohao 2018/3/16
 */
public class ThreadTime implements Runnable {
    private static int ii = 0;
    static long t1;

    private static void fff() {
        for (int i = 0; i < 10000000; i++) {
            for (int j = 0; i < 1000000; i++) {
            }
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++) {
                ii++;
            }
        }

        long t2 = Calendar.getInstance().getTimeInMillis();

        System.out.println(t2 - t1 + "ms");
    }

    public static void main(String[] args) {
        t1 = Calendar.getInstance().getTimeInMillis();
        ThreadTime threadTime = new ThreadTime();
        Thread thread = new Thread(threadTime);
        Thread thread1 = new Thread(threadTime);
        thread.start();
        thread1.start();

    }
}
