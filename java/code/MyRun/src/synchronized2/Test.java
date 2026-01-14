package synchronized2;

import synchronized1.Tiket;

public class Test {
    public static void main(String[] args) {
        Ticket t =new Ticket();
        Thread t1 =new Thread(t);
        Thread t2 =new Thread(t);
        Thread t3 =new Thread(t);

        t1.setName("1:");
        t2.setName("2:");
        t3.setName("3:");

        t1.start();
        t2.start();
        t3.start();
    }
}
