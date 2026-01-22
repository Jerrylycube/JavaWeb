import java.util.ArrayList;
import java.util.Scanner;
public class StudentSystem {
    public static void startStudentSystem() {
        
        Scanner input = new Scanner(System.in);
        ArrayList<Student> studentAll = new ArrayList<>();
        systemInit(studentAll);
        //studentShow(studentAll);
        while (true)
        {
            systemStart();
            String option = input.next();
            String index;
            switch (option) {
                case "1":
                    studentAdd(studentAll);
                    break;
                case "2":
                    System.out.println("请输入要删除学生的id");
                    index = input.next();
                    studentDelete(studentAll, index);
                    break;
                case "3":
                    System.out.println("请输入要修改学生的id");
                    index = input.next();
                    studentSet(studentAll, index);
                    break;
                case "4":
                    studentShow(studentAll);
                    break;
                case "5":
                    System.out.println("系统退出");
                    return;   
                default:
                    System.out.println("非法输入");
                    break;
            }
        }


    }

    public static void systemStart() {
        System.out.println("*---------------------------------------------*");
        System.out.println("\t\t学生管理系统");
        System.out.println("1.添加学生");
        System.out.println("2.删除学生");
        System.out.println("3.修改学生");
        System.out.println("4.查看学生");
        System.out.println("5.退出系统");
    }

    public static void systemInit(ArrayList<Student> studentAll)
    {
        Student stu1 = new Student("01", "a", "10", "ab");
        Student stu2 = new Student("02", "b", "11", "cd");
        Student stu3 = new Student("03", "c", "12", "ef");
        studentAll.add(stu1);
        studentAll.add(stu2);
        studentAll.add(stu3);
    }

    public static void studentAdd(ArrayList<Student> studentAll)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入学生的id");
        String id = input.next();
        for (int i = 0; i < studentAll.size(); i++)
        {
            Student stuTemp = studentAll.get(i);
            String idTemp = stuTemp.getSid();
            if (idTemp.equals(id))
            {
                System.out.println("添加失败,学号重复");
                return;
            }
        }
        System.out.println("请输入学生的姓名");
        String name = input.next();
        System.out.println("请输入学生的年龄");
        String age = input.next();
        System.out.println("请输入学生的所在地");
        String address = input.next();

        Student stu = new Student(id, name, age, address);
        studentAll.add(stu);
        System.out.println("添加成功");

    }
    public static void studentDelete(ArrayList<Student> studentAll,String id)
    {
        
        for (int i = 0; i < studentAll.size(); i++) {
            Student stu = studentAll.get(i);
            String sid = stu.getSid();
            if (sid.equals(id)) {
                studentAll.remove(i);
            System.out.println("删除成功");
                return;
            }
        }
        System.out.println("未找到学生id,删除失败");
    }
    public static void studentSet(ArrayList<Student> studentAll,String id)
    {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < studentAll.size(); i++) {
            Student stu = studentAll.get(i);
            String sid = stu.getSid();
            if (sid.equals(id)) {
                System.out.println("请输入学生的id");
                String sidNew = input.next();
                for (int j = 0; j < studentAll.size(); j++)
                {
                    if (j != i) {
                        Student stuTemp = studentAll.get(j);
                        String sidTemp = stu.getSid();
                        if(sidTemp.equals(id))
                        {
                            System.out.println("修改失败,学号重复");
                            return; 
                        }
                    }
                }
                System.out.println("请输入学生的姓名");
                String name = input.next();
                System.out.println("请输入学生的年龄");
                String age = input.next();
                System.out.println("请输入学生的所在地");
                String address = input.next();
                Student stuNew = new Student(sidNew, name, age, address);
                studentAll.set(i, stuNew);
                System.out.println("修改成功");

                return;
            }
        }
        System.out.println("未找到学生id,修改失败"); 
    }
    public static void studentShow(ArrayList<Student> studentAll)
    {
        for (int i = 0; i < studentAll.size(); i++) {
            Student stu = studentAll.get(i);
            System.out.println(stu.getSid() + " "+stu.getName()+" "
                    + stu.getAge() + " " + stu.getAddress());
        }
    }
}

