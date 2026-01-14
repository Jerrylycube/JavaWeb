package HashMapDemo1;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        HashMap<Student, String> m = new HashMap<>();
        Student stu1 = new Student("1", "2");
        Student stu2 = new Student("2", "3");
        Student stu3 = new Student("2", "3");
        m.put(stu1, "111");
        m.put(stu2, "11");
        m.put(stu3, "1");
        m.forEach((stu, value) -> {
            System.out.println(stu + value);
        });
    }
}
