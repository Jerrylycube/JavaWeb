public class Dog extends Animal {
    Dog() {

    }
    
    public Dog(String age, String color,String name) {
        super(age, color,name);
    }
    @Override
    public void eat(String s)
    {
        String age = this.getAge();
        String color = this.getColor();
        System.out.println(age + "的" + color + "颜色的" + "的狗俩只前腿死死的抱住" + s + "猛吃");
    }
    public void lookHome()
    {
        System.out.println("看家");
    }
}
