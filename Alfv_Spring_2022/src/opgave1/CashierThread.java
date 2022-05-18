package opgave1;

public class CashierThread extends Thread {
    private final int MIN_WAIT = 250;
    private final int MAX_WAIT = 500;

    private final Counter custCounter;
    private final Counter counter;
    private boolean shouldRun;

    public CashierThread(Counter custCounter, Counter counter) {
        this.custCounter = custCounter;
        this.counter = counter;
        shouldRun = true;
    }

    public void run() {
        while (shouldRun) {
            while (counter.getCounter() == custCounter.getCounter()) ;

            while (counter.getCounter() != custCounter.getCounter()) {
                sleepRand(MIN_WAIT, MAX_WAIT);
                System.out.printf("[CASH] Cashier is serving number %d.%n", counter.incCounter());
            }

            System.out.println("[BREAK] No more customers to server, for now...");
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
