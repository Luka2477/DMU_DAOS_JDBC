package opgave9;

import java.util.Random;

public class Common {
    private int global;

    public void takesRandomTime(int max) {
        Random rand = new Random(1023);
        int max2 = Math.abs(rand.nextInt()) % max + 1;

        for (int i = 0; i < max2; i++) {
            for (int j = 0; j < max2; j++) {
                j += i;
                j -= i;
            }
        }
    }

    public int getGlobal() {
        return global;
    }

    public void updateGlobal() {
        int temp = global;
        takesRandomTime(100);
        global = temp + 1;
    }
}
