package opgave8;

import java.util.ArrayList;
import java.util.List;

public class LottoThread extends Thread {
    private final List<ArrayList<Integer>> rows;
    private final ArrayList<Integer> selection;
    private final ArrayList<Integer> statistics;

    public LottoThread(ArrayList<ArrayList<Integer>> rows, int start, int end, ArrayList<Integer> selection, ArrayList<Integer> statistics) {
        this.rows = rows.subList(start, end);
        this.selection = selection;
        this.statistics = statistics;
    }

    public void run() {
        for (int i = 0; i < rows.get(0).size() + 1; i++) {
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
}
