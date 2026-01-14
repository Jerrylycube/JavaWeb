package Thread;

public class Test {
    public static void main(String[] args) {
        MyThread mt =new MyThread("as");
        MyThread2 mt2 =new MyThread2();
        mt.setDaemon(true);
        mt2.start();
        mt.start();

    }
}
