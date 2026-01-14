package synchronized1;

public class Tiket extends Thread{
   static int ticket=0;
   static Object obj = new Object();
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj){
                if(ticket<100)
                {
                    ticket++;
                    System.out.println(getName()+"++ "+ticket);
                }
                else
                    break;
            }
        }
    }
}
