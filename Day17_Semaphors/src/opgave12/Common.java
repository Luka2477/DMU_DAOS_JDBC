package opgave12;

import java.util.concurrent.Semaphore;

public class Common {
    public Semaphore semaphore;
    private int global;

    public Common() {
        semaphore = new Semaphore(1);
    }

    public int getGlobal() {
        return global;
    }

    public synchronized void updateGlobal() {
        int temp = global;
        global = temp + 1;
    }
}
