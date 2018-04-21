package thread;

/**
 * @author caohao 2018/3/2
 */
public class MyThread extends Thread {
    private  static  int count = 0;
    private String threadName;

    public MyThread(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        synchronized (Main.lockA) {
            System.out.println("线程id:" + Thread.currentThread().getId() + threadName + "获得a锁");
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + "等待获得B锁");
            synchronized (Main.lockB) {
                System.out.println(threadName + "获得b锁");
            }
        }
    }
}
