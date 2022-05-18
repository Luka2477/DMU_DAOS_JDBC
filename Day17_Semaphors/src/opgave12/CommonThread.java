package opgave12;

public class CommonThread extends Thread {
    private final Common common;

    public CommonThread(String name, Common common) {
        super(name);
        this.common = common;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            common.updateGlobal();
        }
    }
}
