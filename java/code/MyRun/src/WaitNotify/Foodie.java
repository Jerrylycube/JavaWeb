package WaitNotify;

public class Foodie extends Thread{
    @Override
    public void run() {
        //循环
        //同步代码块
        //判断共享数据是否到末尾（到了末尾
        // 没有到末尾）

        while(true){
            synchronized (Desk.lock){
                if(Desk.count==0){
                    break;
                }
                else{
                    //先去判断桌子上有没有面条，没有就等等，有就吃
                    if(Desk.foodFlag==0){
                        try {
                            Desk.lock.wait();//与lock绑定
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }else {
                        System.out.println("开吃，还剩"+ --Desk.count);
                        Desk.foodFlag=0;
                        Desk.lock.notify();//唤醒绑定在这把锁上的所有线程
                    }
                }
            }
        }

    }
}
