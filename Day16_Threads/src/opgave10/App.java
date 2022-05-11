package opgave10;

import java.util.ArrayList;

public class App {
    private static int ROW_COUNT;

    public static void main(String[] args) throws InterruptedException {
        final int NUMBER_COUNT = 7;
        final int MAX_NUMBER = 36;
        ROW_COUNT = 10000000;

        //Lotto lotto = new Lotto(NUMBER_COUNT, MAX_NUMBER, ROW_COUNT, 2);
        Lotto lotto = new Lotto(NUMBER_COUNT, MAX_NUMBER, ROW_COUNT, 4);

        long start = System.nanoTime();
        lotto.createRows();
        long stop = System.nanoTime();
        System.out.printf("Det tog %fms at generere %,d rækker.%n", (stop - start) / 1000000.0, ROW_COUNT);

        start = System.nanoTime();
        lotto.createSelection();
        stop = System.nanoTime();
        System.out.printf("Det tog %fms at generere %,d træknumre.%n", (stop - start) / 1000000.0, NUMBER_COUNT);

        //run2Threads(lotto);
        run4Threads(lotto);

        ArrayList<Integer> statistics = lotto.getStatistics();
        System.out.printf("Træknumrene er: %s%n", lotto.getSelection());
        for (int i = 0; i < statistics.size(); i++) {
            System.out.printf("%3d rigtige: %,d%n", i, statistics.get(i));
        }
        System.out.printf("Samlede kontrollerede rækker: %,d", statistics.stream().mapToInt(n -> n).sum());
    }

    public static void run2Threads(Lotto lotto) throws InterruptedException {
        LottoThread t1 = new LottoThread(lotto, 0, ROW_COUNT / 2, 0);
        LottoThread t2 = new LottoThread(lotto, ROW_COUNT / 2, ROW_COUNT, 1);
        long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long stop = System.nanoTime();
        System.out.printf("Det tog %fms at generere statistik på %,d rækker, med 2 tråde.%n", (stop - start) / 1000000.0, ROW_COUNT);
    }

    public static void run4Threads(Lotto lotto) throws InterruptedException {
        LottoThread t1 = new LottoThread(lotto, 0, ROW_COUNT / 4, 0);
        LottoThread t2 = new LottoThread(lotto, ROW_COUNT / 4, ROW_COUNT / 2, 1);
        LottoThread t3 = new LottoThread(lotto, ROW_COUNT / 2, ROW_COUNT / 4 * 3, 2);
        LottoThread t4 = new LottoThread(lotto, ROW_COUNT / 4 * 3, ROW_COUNT, 3);
        long start = System.nanoTime();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        long stop = System.nanoTime();
        System.out.printf("Det tog %fms at generere statistik på %,d rækker, med 4 tråde.%n", (stop - start) / 1000000.0, ROW_COUNT);
    }
}
