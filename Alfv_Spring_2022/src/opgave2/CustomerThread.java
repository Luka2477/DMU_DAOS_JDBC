package opgave2;

import java.util.concurrent.Semaphore;

public class CustomerThread extends Thread {
    private final int MIN_WAIT = 100;
    private final int MAX_WAIT = 250;

    private final Counter counter;
    private final Semaphore sem;
    private final Semaphore cashSem;
    private int amount;

    public CustomerThread(String name, Counter counter, int amount, Semaphore sem, Semaphore cashSem) {
        super(name);
        this.counter = counter;
        this.amount = amount;
        this.sem = sem;
        this.cashSem = cashSem;
    }

    public void run() {
        for (; amount > 0; amount--) {
            try {
                sem.acquire();
                runCritical();
                sem.release();

                Sleep.sleepRand(MIN_WAIT, MAX_WAIT);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void runCritical() {
        counter.incCounter();
        System.out.printf("[CUST] Someone picked number %s from %s.%n", counter, getName());
        cashSem.release();
    }
}
