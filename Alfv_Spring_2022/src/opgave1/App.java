package opgave1;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Counter custCounter = new Counter(100, 200);
        Counter cashCounter = new Counter(100, 200);

        CustomerThread custThread1 = new CustomerThread("left entrance", custCounter, 10, 0);
        CustomerThread custThread2 = new CustomerThread("right entrance", custCounter, 10, 1);
        CashierThread cashTread = new CashierThread(custCounter, cashCounter);
        custThread1.start();
        custThread2.start();
        cashTread.start();

        custThread1.join();
        System.out.printf("[DONE] No more customers at %s.%n", custThread1.getName());
        custThread2.join();
        System.out.printf("[DONE] No more customers at %s.%n", custThread2.getName());
        cashTread.toggleShouldRun();
        cashTread.join();
        System.out.println("[DONE] No more customers to serve.");
    }
}
