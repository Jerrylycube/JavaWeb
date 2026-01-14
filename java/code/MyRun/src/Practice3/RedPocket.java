package Practice3;

import java.util.Random;

public class RedPocket extends Thread{
   static Object lock = new Object();
   static int count =3;
   static double money=100;
   static double moneyMin=0.01;
    @Override
    public void run() {
            synchronized (lock){
                if(count==0){
                    System.out.println(getName()+"没有抢到");
                }
                else {
                    if(count==1){
                        System.out.println(getName()+"抢到了"+money);
                    }
                    else {
                        Random r = new Random();
                        double moneyMax = money - (count -1)*moneyMin;
                        double prize = r.nextDouble(moneyMax);
                        if(prize<moneyMin)prize =moneyMin;
                        System.out.println(getName()+"抢到了"+prize);
                        money-=prize;
                    }
                    count--;

                }
            }

    }
}
