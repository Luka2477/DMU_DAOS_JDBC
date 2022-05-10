package opgave8;

import java.util.ArrayList;

public class App {
    private static int ROW_COUNT;

    private static ArrayList<ArrayList<Integer>> rows;
    private static ArrayList<Integer> selection;

    public static void main(String[] args) throws InterruptedException {
        final int NUMBER_COUNT = 7;
        final int MAX_NUMBER = 36;
        ROW_COUNT = 10000000;

        Lotto lotto = new Lotto(NUMBER_COUNT, MAX_NUMBER, ROW_COUNT);

        long start = System.nanoTime();
        lotto.createRows();
        long stop = System.nanoTime();
        rows = lotto.getRows();
        System.out.printf("Det tog %fms at generere %,d rækker.%n", (stop - start) / 1000000.0, ROW_COUNT);

        start = System.nanoTime();
        lotto.createSelection();
        stop = System.nanoTime();
        selection = lotto.getSelection();
        System.out.printf("Det tog %fms at generere %,d træknumre.%n", (stop - start) / 1000000.0, NUMBER_COUNT);

        ArrayList<Integer> statistics1 = new ArrayList<>();
        ArrayList<Integer> statistics2 = new ArrayList<>();
        ArrayList<Integer> statistics3 = new ArrayList<>();
        ArrayList<Integer> statistics4 = new ArrayList<>();

        //run2Threads(statistics1, statistics2);
        run4Threads(statistics1, statistics2, statistics3, statistics4);

        ArrayList<Integer> statistics = joinStatistics(statistics1, statistics2, statistics3, statistics4);

        System.out.printf("Træknumrene er: %s%n", lotto.getSelection());
        for (int i = 0; i < statistics.size(); i++) {
            System.out.printf("%3d rigtige: %,d%n", i, statistics.get(i));
        }
        System.out.printf("Samlede kontrollerede rækker: %,d", statistics.stream().mapToInt(n -> n).sum());
    }

    @SafeVarargs
    public static void run2Threads(ArrayList<Integer>... statistics) throws InterruptedException {
        LottoThread t1 = new LottoThread(rows, 0, ROW_COUNT / 2, selection, statistics[0]);
        LottoThread t2 = new LottoThread(rows, ROW_COUNT / 2, ROW_COUNT, selection, statistics[1]);
        long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long stop = System.nanoTime();
        System.out.printf("Det tog %fms at generere statistik på %,d rækker, med 2 tråde.%n", (stop - start) / 1000000.0, ROW_COUNT);
    }

    @SafeVarargs
    public static void run4Threads(ArrayList<Integer>... statistics) throws InterruptedException {
        LottoThread t1 = new LottoThread(rows, 0, ROW_COUNT / 4, selection, statistics[0]);
        LottoThread t2 = new LottoThread(rows, ROW_COUNT / 4, ROW_COUNT / 2, selection, statistics[1]);
        LottoThread t3 = new LottoThread(rows, ROW_COUNT / 2, ROW_COUNT / 4 * 3, selection, statistics[2]);
        LottoThread t4 = new LottoThread(rows, ROW_COUNT / 4 * 3, ROW_COUNT, selection, statistics[3]);
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

    @SafeVarargs
    public static ArrayList<Integer> joinStatistics(ArrayList<Integer>... statistics) {
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i < statistics[0].size(); i++) {
            res.add(0);
        }

        for (ArrayList<Integer> s : statistics) {
            for (int i = 0; i < s.size(); i++) {
                res.set(i, res.get(i) + s.get(i));
            }
        }

        return res;
    }
}
