import java.util.Random;
public class Practice1_3 {
    
    public static void main(String[] args) {
        //生成验证码，内容：可以是小写字母，也可以是大写字母，还可以是数字
        //4个字母，1个数字，随便位置
        //先生成验证码，再随机打乱位置
        char[] c = new char[5];
        char[] cbank = new char[52];
        char[] nbank = new char[10];
        for (int i = 0; i < 26; i++) {
            cbank[i] =(char) (i + 'a');
        }
        for (int i = 26; i < 52; i++) {
            cbank[i] =(char)( i - 26 + 'A');
        }
        for (int i = 0; i < 10; i++)
        {
            nbank[i] = (char)(i+'0');
        }
        Random r = new Random();
        for (int i = 0; i < c.length; i++) {
            int randomC = r.nextInt(52);
            if (i != c.length - 1) {
                c[i] = cbank[randomC];
            } else {
                int randomN = r.nextInt(10);
                c[i] = nbank[randomN];
            }
        }
        
        for (int idx = 0; idx < c.length; idx++) {
            int randomIndex = r.nextInt(c.length - 1);
            char temp = c[randomIndex];
            c[randomIndex] = c[idx];
            c[idx] = temp;
        }
        
        String result = new String(c);
        System.out.print(result);

    }
}
