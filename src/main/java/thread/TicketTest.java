package thread;

public class TicketTest {
    public static void main(String[] args) {
        Ticket ticket = new Ticket(5);
        Thread thread1 = new Thread(ticket, "窗口01");
        Thread thread2 = new Thread(ticket, "窗口02");
        Thread thread3 = new Thread(ticket, "窗口03");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}