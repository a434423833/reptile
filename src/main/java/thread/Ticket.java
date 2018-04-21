package thread;

public class Ticket implements Runnable {
    private int num;//票数量
    private boolean flag = true;//若为false则售票停止

    public Ticket(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        while (flag) {
            ticket();
        }
    }

    private void ticket() {
        boolean bl = true;
        while (bl) {
            int a = num;
            if (num <= 0) {
                flag = false;
                return;
            }
            try {
                //模拟延时操作
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             if (a == num) {
                System.out.println("窗口" + Thread.currentThread().getId() + "出售序列号" + num--);
                bl = false;
            }
        }
    }
}