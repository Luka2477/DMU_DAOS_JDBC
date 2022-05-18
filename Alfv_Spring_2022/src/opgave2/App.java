package opgave2;

import java.util.concurrent.Semaphore;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Counter custCounter = new Counter(100, 200);
        Counter cashCounter = new Counter(100, 200);

        Semaphore custSem = new Semaphore(1);
        Semaphore cashSem = new Semaphore(0);

        CustomerThread custThread1 = new CustomerThread("left entrance", custCounter, 10, custSem, cashSem);
        CustomerThread custThread2 = new CustomerThread("right entrance", custCounter, 10, custSem, cashSem);
        CashierThread cashTread = new CashierThread(cashCounter, cashSem);
        custThread1.start();
        custThread2.start();
        cashTread.start();

        custThread1.join();
        System.out.printf("[DONE] No more customers at %s.%n", custThread1.getName());

        custThread2.join();
        System.out.printf("[DONE] No more customers at %s.%n", custThread2.getName());

        // toggleShouldRun() kan kommenteres ud, hvis ekspedienten skal blive ved at vente på kunder.
        cashTread.toggleShouldRun();
        cashTread.join();
        System.out.println("[DONE] No more customers to serve.");
    }
}
