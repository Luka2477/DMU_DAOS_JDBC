package opgave3;

public class CashierThread extends Thread {
    private final int MIN_WAIT = 200;
    private final int MAX_WAIT = 350;

    private final Counter counter;
    private final Counter custCounter;
    private boolean shouldRun;

    public CashierThread(Counter counter, Counter custCounter) {
        this.counter = counter;
        this.custCounter = custCounter;
        shouldRun = true;
    }

    public synchronized void run() {
        while (shouldRun || counter.getCounter() != custCounter.getCounter()) {
            try {
                serve();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private synchronized void serve() throws InterruptedException {
        while (counter.getCounter() == custCounter.getCounter()) {
            System.out.println("[BREAK] No more customers to serve, for now...");
            wait();
        }

        Sleep.sleepRand(MIN_WAIT, MAX_WAIT);
        int count = counter.incCounter();
        System.out.printf("[CASH] Cashier is serving number %d.%n", count);
        notifyAll();
    }

    public void toggleShouldRun() {
        shouldRun = !shouldRun;
    }
}
