package opgave10;

import java.util.ArrayList;

public class Lotto {
    private final int NUMBER_COUNT;
    private final int MAX_NUMBER;
    private final int ROW_COUNT;

    private final ArrayList<ArrayList<Integer>> rows;
    private final ArrayList<Integer> selection;
    private final ArrayList<Integer> statistics;
    private final boolean[] flag;
    private int turn;

    public Lotto(int numberCount, int maxNumber, int rowCount, int flagCount) {
        NUMBER_COUNT = numberCount;
        MAX_NUMBER = maxNumber + 1;
        ROW_COUNT = rowCount;

        rows = new ArrayList<>(ROW_COUNT);
        selection = new ArrayList<>(NUMBER_COUNT);
        statistics = new ArrayList<>(NUMBER_COUNT + 1);
        initStatistics();

        flag = new boolean[flagCount];
    }

    public void createRows() {
        for (int i = 0; i < ROW_COUNT; i++) {
            rows.add(new ArrayList<>(MAX_NUMBER));

            for (int j = 0; j < NUMBER_COUNT; j++) {
                rows.get(i).add(randGen(i));
            }
        }
    }

    public ArrayList<ArrayList<Integer>> getRows() {
        return new ArrayList<>(rows);
    }

    public void createSelection() {
        for (int i = 0; i < NUMBER_COUNT; i++) {
            selection.add(randGen(i));
        }
    }

    public ArrayList<Integer> getSelection() {
        return new ArrayList<>(selection);
    }

    public void initStatistics() {
        for (int i = 0; i < NUMBER_COUNT + 1; i++) {
            statistics.add(0);
        }
    }

    public void setStatistic(int index, int value) {
        statistics.set(index, value);
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

    public void setFlag(boolean flag, int id) {
        this.flag[id] = flag;
    }

    public int getFlagSize() {
        return flag.length;
    }

    public boolean getFlag(int id) {
        return flag[id];
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
