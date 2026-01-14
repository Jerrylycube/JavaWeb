import java.util.TreeSet;

public class SetDemo3Student2 {
    public static void main(String[] args) {
        TreeSet<Student2> stu = new TreeSet<>();
        Student2 s1 = new Student2("as", 12, 11, 22, 33);
        Student2 s2 = new Student2("asd", 12, 11, 23, 33);
        Student2 s3 = new Student2("ass", 12, 12, 22, 33);
        Student2 s4 = new Student2("as5", 12, 12, 22, 34);
        Student2 s5 = new Student2("as6", 12, 12, 22, 34);
        stu.add(s1);
        stu.add(s2);
        stu.add(s3);
        stu.add(s4);
        stu.add(s5);
        for (Student2 student2 : stu) {
            System.out.println(student2);
        }
    }
}
