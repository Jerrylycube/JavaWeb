package Practice4;

import java.util.ArrayList;
import java.util.Collections;

public class Box extends Thread{
    static Object lock =new Object();
    private ArrayList<Integer>list;
    public Box(ArrayList<Integer>list) {
        this.list=list;
    }

    @Override
    public void run() {
        while(true){

            synchronized (lock){
                if(list.isEmpty()){
                    break;
                }
                else {
                    Collections.shuffle(list);
                    System.out.println(getName()+"  "+list.get(0));
                    list.remove(0);
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
