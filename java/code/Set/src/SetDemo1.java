import java.util.TreeSet;

public class SetDemo1 {
    public static void main(String[] args) {
        //创建集合对象

        TreeSet<Student> ts = new TreeSet<>();
        //创建学生对象
        Student s1 = new Student("zhangsan", 28);
        Student s2 = new Student("lisi", 27);
        Student s3 = new Student("wangwu", 29);
        Student s4 = new Student("zhaoliu", 28);
        Student s5 = new Student("qianqi", 30);
        //把学生添加到集合
        ts.add(s1);
        ts.add(s2);
        ts.add(s3);
        ts.add(s4);
        ts.add(s5);
        //遍历集合
        for (Student student : ts) {
            System.out.println(student);
        }
        
    }
}