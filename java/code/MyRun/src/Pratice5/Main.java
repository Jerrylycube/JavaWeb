package Pratice5;


import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 10, 5, 20, 50, 100, 200, 500, 800, 2, 80, 300, 700);
        Box box1 = new Box(list);
        Box box2 = new Box(list);

        FutureTask<Integer> ft1 = new FutureTask<>(box1);
        FutureTask<Integer> ft2 = new FutureTask<>(box2);

        Thread t1 = new Thread(ft1);
        Thread t2 = new Thread(ft2);
        t1.setName("box1");
        t2.setName("box2");
        t1.start();
        t2.start();

        Integer m1 = ft1.get();
        Integer m2 = ft2.get();

        if (m1 > m2) System.out.println("1  " + m1);
        else System.out.println("2  " + m2);
    }
}
