package opgave3;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Counter custCounter = new Counter(100, 1000);
        Counter cashCounter = new Counter(100, 1000);

        CustomerThread custThread1 = new CustomerThread("left entrance", custCounter, 10);
        CustomerThread custThread2 = new CustomerThread("right entrance", custCounter, 10);
        CashierThread cashThread = new CashierThread(cashCounter, custCounter);
        custThread1.start();
        custThread2.start();
        cashThread.start();

        custThread1.join();
        System.out.printf("[DONE] No more customers at %s.%n", custThread1.getName());

        custThread2.join();
        System.out.printf("[DONE] No more customers at %s.%n", custThread2.getName());

        // toggleShouldRun() kan kommenteres ud, hvis ekspedienten skal blive ved at vente på kunder.
        cashThread.toggleShouldRun();
        cashThread.join();
        System.out.println("[DONE] No more customers to serve.");
    }
}
