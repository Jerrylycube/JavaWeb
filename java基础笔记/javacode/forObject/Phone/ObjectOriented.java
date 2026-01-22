public class ObjectOriented {
    public static void main(String[] args) {
        Phone p = new Phone();
        p.price = 123.44;
        p.brand = "»ªÎª";
        System.out.println(p.brand);
        System.out.println(p.price);

        p.call();
        p.playGame();
    }

}
