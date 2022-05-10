package given;

public class threadClass extends Thread {
    String dyrelyd;

    public threadClass(String dyrelyd) {
        super();
        this.dyrelyd = dyrelyd;
    }

    public void run() {
        System.out.println(dyrelyd);
    }
}
