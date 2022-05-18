package opgave2;

import java.util.concurrent.Semaphore;

public class CashierThread extends Thread {
    private final int MIN_WAIT = 200;
    private final int MAX_WAIT = 300;

    private final Counter counter;
    private final Semaphore sem;
    private boolean shouldRun;

    public CashierThread(Counter counter, Semaphore sem) {
        this.counter = counter;
        this.sem = sem;
        shouldRun = true;
    }

    public void run() {
        while (shouldRun || sem.availablePermits() != 0) {
            try {
                sem.acquire();
                Sleep.sleepRand(MIN_WAIT, MAX_WAIT);
                int count = counter.incCounter();
                System.out.printf("[CASH] Cashier is serving number %d.%n", count);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (sem.availablePermits() == 0) {
                System.out.println("[BREAK] No more customers to serve, for now...");
            }
        }
    }

    public void toggleShouldRun() {
        shouldRun = !shouldRun;
    }
}
