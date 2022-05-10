package opgave7;

public class threadClass extends Thread {
    String dyrelyd;

    public threadClass(String dyrelyd) {
        super();
        this.dyrelyd = dyrelyd;
    }

    public void run() {
        try {
            sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(dyrelyd);
    }
}
