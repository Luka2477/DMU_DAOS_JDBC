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
                sleepRand(MIN_WAIT, MAX_WAIT);
                System.out.printf("[CASH] Cashier is serving number %d.%n", counter.incCounter());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void toggleShouldRun() {
        shouldRun = !shouldRun;
    }

    private void sleepRand(int min, int max) {
        try {
            sleep((long) (min + (Math.random() * (max - min))));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
