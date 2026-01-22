public class Person {
    private String name;
    private String gender;
    private String age;
    private String nationality;

    public static void w(){
        System.out.println("2222");
    }
    public Person() {
    }

    public Person(String age, String gender, String name, String nationality) {
        this.age = age;
        this.gender = gender;
        this.name = name;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void eat() {
        System.out.println("³Ô·¹");
    }

    public  void sleep() {
        System.out.println("Ë¯¾õ");
    }

    public  void work() {
        System.out.println("¹¤×÷");
    }
}
