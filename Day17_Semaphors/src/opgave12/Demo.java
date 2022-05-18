package opgave12;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Common common = new Common();
        CommonThread t1 = new CommonThread("t1", common);
        CommonThread t2 = new CommonThread("t2", common);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(common.getGlobal());
    }
}
