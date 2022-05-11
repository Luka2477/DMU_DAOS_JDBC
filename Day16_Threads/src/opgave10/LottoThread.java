package opgave10;

import java.util.ArrayList;
import java.util.List;

public class LottoThread extends Thread {
    private final Lotto lotto;
    private final int thisId;

    private final List<ArrayList<Integer>> rows;

    public LottoThread(Lotto lotto, int start, int end, int id) {
        this.lotto = lotto;
        this.thisId = id;
        this.rows = lotto.getRows().subList(start, end);
    }

    public void run() {
        int concurrentId = (thisId + 1) % lotto.getFlagSize();
        lotto.setFlag(true, thisId);
        lotto.setTurn(concurrentId);
        while (lotto.getFlag(concurrentId) && lotto.getTurn() == concurrentId) ;

        for (ArrayList<Integer> arr : rows) {
            int count = 0;

            for (int num : arr) {
                if (lotto.getSelection().contains(num)) {
                    count++;
                }
            }

            lotto.setStatistic(count, lotto.getStatistics().get(count) + 1);
        }

        lotto.setFlag(false, thisId);
    }
}
