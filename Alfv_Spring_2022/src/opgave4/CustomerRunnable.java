package opgave4;

public class CustomerRunnable implements Runnable {
    private final int MIN_SLEEP = 100;
    private final int MAX_SLEEP = 250;

    private final CircularQueue ticketQueue;

    private boolean open;

    public CustomerRunnable(CircularQueue sharedQueue) {
        ticketQueue = sharedQueue;
        open = true;
    }

    @Override
    public void run() {
        while (open) ;
    }

    public void takeTicket(String name) throws InterruptedException {
        synchronized (ticketQueue) {
            while (ticketQueue.isFull()) {
                System.out.println("[CUST] No more people allowed in the building at this moment.");
                ticketQueue.wait();
            }

            ticketQueue.enqueue(name);
            System.out.printf("[CUST] %s took a ticket.%n", name);
            ticketQueue.notifyAll();
        }
        Sleep.sleepRand(MIN_SLEEP, MAX_SLEEP);
    }

    public void close() {
        open = false;
    }
}
