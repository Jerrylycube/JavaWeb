package WaitNotifyQUe;

import java.util.concurrent.ArrayBlockingQueue;

public class Test {
    public static void main(String[] args) {
        ArrayBlockingQueue<String>queue=new ArrayBlockingQueue<>(2);

        Cook c = new Cook(queue);
        Foodie f  =new Foodie(queue);
        c.start();
        f.start();
    }
}
