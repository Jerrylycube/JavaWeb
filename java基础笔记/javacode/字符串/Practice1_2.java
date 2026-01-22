import java.util.Random;
import java.util.Scanner;
public class Practice1_2 {
    public static void main(String[]arg)
    {
        Scanner input = new Scanner(System.in);
        String str = input.next();
        char[] c = new char[str.length()];
        //复制一份成字符数组
        for (int idx = 0; idx < str.length(); idx++) {
            c[idx] = str.charAt(idx);
        }
        //进行打乱
        Random r = new Random();
        for (int idx = 0; idx < c.length; idx++) {
            int randomIndex = r.nextInt(c.length-1);
            char temp = c[randomIndex];
            c[randomIndex] = c[idx];
            c[idx] = temp;
        }
        //再转换回来
        String result = new String(c);
        System.out.println(result);

        
    }
    
}
