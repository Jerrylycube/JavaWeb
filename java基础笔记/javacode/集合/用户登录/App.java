import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<User> userAll = new ArrayList<>();
        User u1 = new User("L123", "123456", "440882200309191834", "13682224984");
        userAll.add(u1);
        while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作1登录 2注册 3忘记密码 4系统退出");
            String option = input.next();
            switch (option) {
                case "1":
                    logIn();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    System.out.println("退出系统成功");
                    break;
                default:
                    System.out.println("非法输入");
                    break;
            }
        }
    }

    public static void logIn()
    {
        Scanner input = new Scanner(System.in);
        //用户名检查
        while (true) {
            System.out.println("请输入用户名");
            String name = input.next();
            boolean flag1 = isNameLength(name);
            boolean flag2 = is
            if(flag1&&)
            
        }

    }

    public static boolean isNameContain(ArrayList<User> userAll, String name)
    {
        for (int idx = 0; idx < userAll.size(); idx++) {
            User u = userAll.get(idx);
            if (name.equals(u.getName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNameLength(String name)
    {
        int length = name.length();
        if (length >= 3 && length <= 15)
            return true;
        return false;
    }

    public static boolean isNameRight(String name)
    {
        for (int idx = 0; idx < name.length(); idx++) {
            char c = name.charAt(idx);
            //在数字和字母的范围之外
           if(!((c>='a'&&c<='z')||(c>='A'&&c<='Z')||(c>='0'&&c<='9')))
           {
               return false;
           }
           //判断有没有字母
           
            
        }
    }

}