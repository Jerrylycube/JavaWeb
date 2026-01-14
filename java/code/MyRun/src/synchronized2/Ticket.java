package synchronized2;

public class Ticket implements Runnable{
    int ticket =0;
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                if (method()) break;
        }
    }

    private synchronized boolean method() {
        if(ticket<100)
        {
            ticket++;
            System.out.println(Thread.currentThread().getName()+"++ "+ticket);
        }
        else
            return true;
        return false;
    }
}
