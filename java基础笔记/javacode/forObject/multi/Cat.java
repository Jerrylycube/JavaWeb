public class Cat extends Animal
{

    public Cat() {
    }

    public Cat(String age, String color,String name) {
        super(age, color,name);
    }
    
    @Override
    public void eat(String s)
    {
        String age = this.getAge();
        String color = this.getColor();
        System.out.println(age + "的" + color + "颜色的" + "的猫咪着眼睛侧着头" + "吃" + s);
    }

    public void catchMouse()
    {
        System.out.println("抓老鼠");
    }
}