package opgave1;

public class CustomerThread extends Thread {
    private final int MIN_WAIT = 100;
    private final int MAX_WAIT = 250;

    private final Counter counter;
    private final int thisId;
    private int amount;

    public CustomerThread(String name, Counter counter, int amount, int id) {
        super(name);
        this.counter = counter;
        this.amount = amount;
        this.thisId = id;
    }

    public void run() {
        int concurrentId = (thisId + 1) % 2;

        for (; amount > 0; amount--) {
            counter.setFlag(thisId, true);
            counter.setTurn(concurrentId);
            while (counter.getFlag(concurrentId) && counter.getTurn() == concurrentId) ;

            runCritical();

            counter.setFlag(thisId, false);
        }
    }

    private void runCritical() {
        counter.incCounter();
        System.out.printf("[CUST] Someone picked number %s from %s.%n", counter, getName());
        Sleep.sleepRand(MIN_WAIT, MAX_WAIT);
    }
}
