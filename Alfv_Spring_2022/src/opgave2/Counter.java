package opgave2;

public class Counter {
    private final int COUNTER_INIT;
    private final int COUNTER_MAX;
    private int counter;

    public Counter(int COUNTER_INIT, int COUNTER_MAX) {
        this.COUNTER_INIT = COUNTER_INIT;
        this.COUNTER_MAX = COUNTER_MAX;
        counter = COUNTER_INIT;
    }

    public int incCounter() {
        int currCount = counter;
        if (counter >= COUNTER_MAX) counter = COUNTER_INIT;
        else counter++;
        return currCount;
    }

    public int getCounter() {
        return counter;
    }
}
