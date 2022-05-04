package opgave6;

public class Lotto {

    private final int MAX_NUMBER;
    private final int ROWS;

    private final int[][] note;

    public Lotto(int maxNumber, int rows) {
        MAX_NUMBER = maxNumber;
        ROWS = rows;

        note = new int[ROWS][MAX_NUMBER];

        create();
    }

    public void create() {

    }

}
