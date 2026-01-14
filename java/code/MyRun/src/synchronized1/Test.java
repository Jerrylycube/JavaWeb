package synchronized1;

public class Test {
    public static void main(String[] args) {
        Tiket t1 =new Tiket();
        Tiket t2 =new Tiket();
        Tiket t3 =new Tiket();
        t1.setName("1:");
        t2.setName("2:");
        t3.setName("3:");
        t1.start();
        t2.start();
        t3.start();
    }
}
