package opgave6;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        final int NUMBER_COUNT = 7;
        final int MAX_NUMBER = 36;
        final int ROW_COUNT = 10000000;

        Lotto lotto = new Lotto(NUMBER_COUNT, MAX_NUMBER, ROW_COUNT);

        long start = System.nanoTime();
        lotto.createRows();
        long stop = System.nanoTime();
        System.out.printf("Det tog %fms at generere %,d rækker.%n", (stop - start) / 1000000.0, ROW_COUNT);

        start = System.nanoTime();
        lotto.createSelection();
        stop = System.nanoTime();
        System.out.printf("Det tog %fms at generere %,d træknumre.%n", (stop - start) / 1000000.0, NUMBER_COUNT);

        start = System.nanoTime();
        lotto.createStatistics();
        stop = System.nanoTime();
        System.out.printf("Det tog %fms at generere statistik på %,d rækker.%n", (stop - start) / 1000000.0, ROW_COUNT);

        System.out.printf("Træknumrene er: %s%n", lotto.getSelection());
        ArrayList<Integer> statistics = lotto.getStatistics();
        for (int i = 0; i < statistics.size(); i++) {
            System.out.printf("%3d rigtige: %,d%n", i, statistics.get(i));
        }
        System.out.printf("Samlede kontrollerede rækker: %,d", statistics.stream().mapToInt(n -> n).sum());
    }
}
