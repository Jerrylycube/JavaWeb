package Practice1;

public class windows extends Thread{
    public static int count =0;
    public static Object lock = new Object();
    @Override
    public void run() {
        while(true){
            synchronized (lock){
                if(count==100){
                    System.out.println("卖完了");
                    break;
                }
                else {
                    count++;
                    System.out.println(getName()+"卖了第"+count+"张票"+",还剩下"+(1000-count));
                }
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
