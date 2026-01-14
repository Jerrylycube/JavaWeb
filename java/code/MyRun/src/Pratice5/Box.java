package Pratice5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Callable;

public class Box implements Callable<Integer> {
    static Object lock = new Object();
    private ArrayList<Integer> list;

    public Box(ArrayList<Integer> list) {
        this.list = list;
    }

    @Override
    public Integer call() throws Exception {
        ArrayList<Integer> listB = new ArrayList<>();
        Integer sum = 0;
        while (true) {
            synchronized (lock) {
                if (list.isEmpty()) {
                    if (listB.isEmpty()) return 0;
                    System.out.println(Thread.currentThread().getName() + " " +
                            +listB.size() + "  " + listB + "  " + sum);
                    return Collections.max(listB);
                } else {
                    Collections.shuffle(list);
                    Integer prize = list.remove(0);
                    listB.add(prize);
                    sum += prize;
                }

            }
            Thread.sleep(10);
        }
    }
}
