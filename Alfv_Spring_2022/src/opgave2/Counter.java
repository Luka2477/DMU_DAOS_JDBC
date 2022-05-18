package opgave2;

public class Counter {
    private final int COUNTER_MAX;
    private int counter;

    public Counter(int COUNTER_INIT, int COUNTER_MAX) {
        this.COUNTER_MAX = COUNTER_MAX;
        counter = COUNTER_INIT;
    }

    public int incCounter() {
        return ++counter % COUNTER_MAX;
    }

    public int getCounter() {
        return counter;
    }
}
