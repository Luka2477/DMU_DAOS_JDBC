package opgave3;

public class Counter {
    private final int COUNTER_MAX;
    private int counter;

    public Counter(int COUNTER_INIT, int COUNTER_MAX) {
        this.COUNTER_MAX = COUNTER_MAX;
        counter = COUNTER_INIT;
    }

    public synchronized void incCounter() {
        ++counter;
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return Integer.toString(counter % COUNTER_MAX);
    }
}
