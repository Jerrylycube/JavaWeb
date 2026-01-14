package Practice2;


public class Main {
    public static void main(String[] args) {
        MyThread w1=new MyThread();
        MyThread w2 =new MyThread();
        w1.setName("线程1");
        w2.setName("线程2");
        w1.start();
        w2.start();
    }
}
