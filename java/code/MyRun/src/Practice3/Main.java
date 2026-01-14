package Practice3;

public class Main {
    public static void main(String[] args) {
        RedPocket p1 =new RedPocket();
        RedPocket p2 =new RedPocket();
        RedPocket p3 =new RedPocket();
        RedPocket p4 =new RedPocket();
        RedPocket p5 =new RedPocket();
        p1.setName("1");
        p2.setName("2");
        p3.setName("3");
        p4.setName("4");
        p5.setName("5");
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
    }
}
