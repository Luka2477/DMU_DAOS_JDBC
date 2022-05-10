package opgave7;

public class Traadaftestning {
    public static void main(String[] args) {
        System.out.println("Vi er igang");

        threadClass t1 = new threadClass("MUUH");
        threadClass t2 = new threadClass("ØF");
        threadClass t3 = new threadClass("Mææh");
        t1.setPriority(3);
        t2.setPriority(1);
        t3.setPriority(2);
        t1.start();
        t2.start();
        t3.start();

        System.out.println("Nu er det slut");
    }
}
