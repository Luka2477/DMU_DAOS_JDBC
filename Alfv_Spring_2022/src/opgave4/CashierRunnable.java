package opgave4;

public class CashierRunnable implements Runnable {
    private final int MIN_SLEEP = 500;
    private final int MAX_SLEEP = 1000;

    private final CircularQueue ticketQueue;

    private boolean open;

    public CashierRunnable(CircularQueue sharedQueue) {
        ticketQueue = sharedQueue;
        open = true;
    }

    @Override
    public void run() {
        while (open) {
            try {
                serve();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void serve() throws InterruptedException {
        synchronized (ticketQueue) {
            while (ticketQueue.isEmpty()) {
                System.out.println("\t[CASH] There are no customers to serve at this moment.");
                ticketQueue.wait();
            }

            String name = ticketQueue.dequeue();
            System.out.printf("\t[CASH] Serving %s.%n", name);
            ticketQueue.notifyAll();
        }
        Sleep.sleepRand(MIN_SLEEP, MAX_SLEEP);
    }

    public void close() {
        open = false;
    }
}
