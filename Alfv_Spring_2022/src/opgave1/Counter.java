package opgave1;

public class Counter {
    private final int COUNTER_INIT;
    private final int COUNTER_MAX;
    private final boolean[] flags;
    private int counter;
    private volatile int turn;

    public Counter(int COUNTER_INIT, int COUNTER_MAX) {
        this.COUNTER_INIT = COUNTER_INIT;
        this.COUNTER_MAX = COUNTER_MAX;
        counter = COUNTER_INIT;

        flags = new boolean[2];
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

    public void setFlag(int id, boolean val) {
        flags[id] = val;
    }

    public boolean getFlag(int id) {
        return flags[id];
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
