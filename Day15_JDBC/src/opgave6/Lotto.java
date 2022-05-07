package opgave6;

import java.util.ArrayList;

public class Lotto {
    private final int NUMBER_COUNT;
    private final int MAX_NUMBER;
    private final int ROW_COUNT;

    private final ArrayList<ArrayList<Integer>> rows;
    private final ArrayList<Integer> selection;
    private final ArrayList<Integer> statistics;

    public Lotto(int numberCount, int maxNumber, int rowCount) {
        NUMBER_COUNT = numberCount;
        MAX_NUMBER = maxNumber + 1;
        ROW_COUNT = rowCount;

        rows = new ArrayList<>(ROW_COUNT);
        selection = new ArrayList<>(NUMBER_COUNT);
        statistics = new ArrayList<>(NUMBER_COUNT + 1);
    }

    public void createRows() {
        for (int i = 0; i < ROW_COUNT; i++) {
            rows.add(new ArrayList<>(MAX_NUMBER));

            for (int j = 0; j < NUMBER_COUNT; j++) {
                rows.get(i).add(randGen(i));
            }
        }
    }

    public void createSelection() {
        for (int i = 0; i < NUMBER_COUNT; i++) {
            selection.add(randGen(i));
        }
    }

    public ArrayList<Integer> getSelection() {
        return new ArrayList<>(selection);
    }

    public void createStatistics() {
        for (int i = 0; i < NUMBER_COUNT + 1; i++) {
            statistics.add(0);
        }

        for (ArrayList<Integer> arr : rows) {
            int count = 0;

            for (int num : arr) {
                if (selection.contains(num)) {
                    count++;
                }
            }

            statistics.set(count, statistics.get(count) + 1);
        }
    }

    public ArrayList<Integer> getStatistics() {
        return new ArrayList<>(statistics);
    }

    private int randGen(int i) {
        int rand = 0;
        while (rand == 0 || rows.get(i).contains(rand)) {
            rand = Math.max((int) Math.ceil(Math.random() * MAX_NUMBER), 1);
        }
        return rand;
    }
}
