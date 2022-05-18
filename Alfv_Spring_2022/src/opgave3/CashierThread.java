package opgave3;

public class CashierThread extends Thread {
    private final int MIN_WAIT = 20;
    private final int MAX_WAIT = 30;

    private final Counter counter;
    private final Counter custCounter;
    private boolean shouldRun;
    private boolean waiting;

    public CashierThread(Counter counter, Counter custCounter) {
        this.counter = counter;
        this.custCounter = custCounter;
        shouldRun = true;
        waiting = true;
    }

    public synchronized void run() {
        while (shouldRun) {
            while (waiting) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            while (counter.getCounter() != custCounter.getCounter()) {
                Sleep.sleepRand(MIN_WAIT, MAX_WAIT);
                counter.incCounter();
                System.out.printf("[CASH] Cashier is serving number %s.%n", counter);
            }

            System.out.println("[BREAK] No more customers to server, for now...");
            waiting = true;
        }
    }

    public void toggleShouldRun() {
        shouldRun = !shouldRun;
    }

    public synchronized void stopWaiting() {
        waiting = false;
        notifyAll();
    }

    public boolean isWaiting() {
        return waiting;
    }
}
