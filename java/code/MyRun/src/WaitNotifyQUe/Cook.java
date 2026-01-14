package WaitNotifyQUe;

import java.util.concurrent.ArrayBlockingQueue;

public class Cook extends Thread{
    ArrayBlockingQueue<String> queue;
    public Cook(ArrayBlockingQueue<String>queue){
        this.queue=queue;
    }
    @Override
    public void run() {
        while (true){
            //放面条到queue中
            try {
                queue.put("面条");//此处put的底层已经写了锁了
                System.out.println("厨师放了一碗面条 ");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
