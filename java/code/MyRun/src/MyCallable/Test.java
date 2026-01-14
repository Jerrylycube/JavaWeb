package MyCallable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable mc =new MyCallable();
        FutureTask<Integer> f =new FutureTask<>(mc);
        Thread t = new Thread(f);
        t.start();
        System.out.println(f.get());
    }
}
