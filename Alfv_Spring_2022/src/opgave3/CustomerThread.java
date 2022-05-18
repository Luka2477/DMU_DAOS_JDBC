package opgave3;

public class CustomerThread extends Thread {
    private final int MIN_WAIT = 10;
    private final int MAX_WAIT = 25;

    private final Counter counter;
    private final CashierThread cashThread;
    private int amount;

    public CustomerThread(String name, Counter counter, CashierThread cashThread, int amount) {
        super(name);
        this.counter = counter;
        this.cashThread = cashThread;
        this.amount = amount;
    }

    public void run() {
        for (; amount > 0; amount--) {
            runCritical();

            if (cashThread.isWaiting()) {
                cashThread.stopWaiting();
            }
            Sleep.sleepRand(MIN_WAIT, MAX_WAIT);
        }
    }

    private void runCritical() {
        counter.incCounter();
        System.out.printf("[CUST] Someone picked number %s from %s.%n", counter, getName());
    }
}
