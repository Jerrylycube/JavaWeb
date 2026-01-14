import java.util.Random;

public class Code {
    public static String getCode() {
        char[] crCode = new char[5];
        Random r = new Random();
        int randomIndex = 0;
        for (int i = 0; i < 5; i++) {
            int randomFlag = r.nextInt(2);//判断随机在大写还是小写

            if (randomFlag == 1) randomIndex = r.nextInt(26) + 65;
            else randomIndex = r.nextInt(26) + 97;

            if (i < 4) crCode[i] = (char) randomIndex;
            else crCode[i] = (char) (r.nextInt(10) + 48);//第五位数是数字
        }
        for (int i = 0; i < 5; i++) {
            randomIndex = r.nextInt(5);
            char temp = crCode[randomIndex];
            crCode[randomIndex] = crCode[i];
            crCode[i] = temp;
        }
        String code = new String(crCode);
        return code;
    }
}
