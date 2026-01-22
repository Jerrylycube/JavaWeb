import  java.util.Random;
public class Person{
    private String name;
    private int blood;

    public Person() {
    }
    
    public Person(int blood, String name) {
        this.blood = blood;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public void attack(Person n1)
    {
        Random r = new Random();
        int blood = r.nextInt(15)+1;
        int remainBlood = n1.getBlood() - blood;
        remainBlood = (remainBlood < 0 ? 0 : remainBlood);
        n1.setBlood(remainBlood);
        System.out.println(this.getName() + "打了" + n1.getName() + blood + "血" + "," +
         n1.getName() + "还剩下" + remainBlood + "血");
    }

    
}