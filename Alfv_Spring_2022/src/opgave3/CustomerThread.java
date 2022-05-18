package opgave3;

public class CustomerThread extends Thread {
    private final int MIN_WAIT = 100;
    private final int MAX_WAIT = 250;

    private final Counter counter;
    private int amount;

    public CustomerThread(String name, Counter counter, int amount) {
        super(name);
        this.counter = counter;
        this.amount = amount;
    }

    public void run() {
        for (; amount > 0; amount--) {
            runCritical();
            Sleep.sleepRand(MIN_WAIT, MAX_WAIT);
        }
    }

    private void runCritical() {
        int count = counter.incCounter();
        System.out.printf("[CUST] Someone picked number %d from %s.%n", count, getName());
    }
}
