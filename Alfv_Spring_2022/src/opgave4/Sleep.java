package opgave4;

public class Sleep {
    public static void sleepRand(int min, int max) {
        try {
            Thread.sleep((long) (min + (Math.random() * (max - min))));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
