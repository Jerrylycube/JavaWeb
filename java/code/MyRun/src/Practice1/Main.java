package Practice1;

public class Main {
    public static void main(String[] args) {
        windows w1=new windows();
        windows w2 =new windows();
        w1.setName("窗口1");
        w2.setName("窗口2");
        w1.start();
        w2.start();
    }
}
