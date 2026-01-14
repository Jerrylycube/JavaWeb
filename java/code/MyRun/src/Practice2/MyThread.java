package Practice2;

import java.util.Random;

public class MyThread extends  Thread{
    public static Object lock = new Object();
    public static int count =1;
    @Override
    public void run() {
        while(true){
            synchronized (lock){
                if(count==101){
                    break;
                }
                else {
                    if(count%2==1) {
                        System.out.println(getName()+" "+ count);

                    }
                }

            }
            count++;//这个要放在外面，放在判断里面的话，到偶数就不会自增了
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
