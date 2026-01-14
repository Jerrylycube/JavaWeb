package Thread;

public class MyThread2 extends  Thread{
    @Override
    public void run() {
//        try {
//            Thread.sleep(11);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        for (int i = 0; i < 10; i++) {
            System.out.println(getName()+"女神");
        }

    }
}
