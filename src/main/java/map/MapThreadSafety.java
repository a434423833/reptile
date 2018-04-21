package map;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 验证map线程不安全
 *
 * @author caohao 2018-04-08
 */
public class MapThreadSafety implements Runnable {

    private static Map<Integer, Integer> map = new HashMap<>();

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            map.put(i, i);
            System.out.println(i);
        }
    }

    private class ThreadTableMap extends Thread {
        Map table = new Hashtable();
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                table.put(i, i);
            }
        }
    }

    private class ThreadConcurrentHashMap extends Thread {
        Map concurrent = new ConcurrentHashMap();

        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                concurrent.put(i, i);
            }
        }
    }

    public static void main(String[] args) {
        MapThreadSafety threadSyncMap = new MapThreadSafety();
        Thread thread1 = new Thread(threadSyncMap);
        Thread thread2 = new Thread(threadSyncMap);
        Thread thread3 = new Thread(threadSyncMap);
        Thread thread4 = new Thread(threadSyncMap);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        for (Integer key : map.keySet()) {
            if (key.intValue() != map.get(key)) {
                System.out.println(key + " :" + map.get(key));
            }
        }

    }

}
