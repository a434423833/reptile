package thread;

/**
 * @author caohao 2018/3/2
 */
public class MyThread1 implements Runnable {
    private static int count = 0;
    private String threadName;

    public MyThread1(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        synchronized (Main.lockB) {
            System.out.println("线程id:" + Thread.currentThread().getId() + threadName + "获得b锁");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + "等待获得A锁");
            synchronized (Main.lockA) {
                System.out.println(threadName + "获得A锁");
            }
        }
    }
}
