import java.util.Scanner;
public class StringDemo{
    public static void main(String[] args) {
        //统计大小写和数字
        Scanner input = new Scanner(System.in);
        String str = input.next();
        int capCnt = 0;
        int miniCnt = 0;
        int mathCnt = 0;
        for (int idx = 0; idx < str.length(); idx++) {
            char c = str.charAt(idx);
            if ((c >= 'a') && (c <= 'z')) {
                miniCnt++;
            }
            if ((c >= 'A') && (c <= 'Z')) {
                capCnt++;
            }
            if ((c >= '0') && (c <= '9')) {
                mathCnt++;
            }
        }
        System.out.println(miniCnt + " " + capCnt + " " + mathCnt);
    }

} 