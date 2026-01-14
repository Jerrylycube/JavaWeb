package Practice4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer>list = new ArrayList<>();
        Collections.addAll(list,10,5,20,50,100,200,500,800,2,80,300,700);

        Box box1 =new Box(list);
        Box box2 =new Box(list);

        box1.setName("抽奖箱1");
        box2.setName("抽奖箱2");

        box1.start();
        box2.start();

    }
}
