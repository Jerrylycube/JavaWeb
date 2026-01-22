
public class Person {
    private String name;
    private String age;

    
    public Person(String age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    
    public void keepPet(Animal a, String s) {
        String pAge = this.age;
        String pName = this.name;
        String AAge = a.getAge();
        String AColor = a.getColor();
        System.out.println("年龄为"+pAge+"岁的"+pName+"养了一只"+AColor+"颜色的"+AAge+"岁的"+a.getName());
        a.eat(s);
        if(a instanceof Dog d)
        {
            d.lookHome();
        }
        else if(a instanceof Cat c)
        {
            c.catchMouse();
        }
        else
        {
            System.err.println("类型转换错误");
        }
    }
}
