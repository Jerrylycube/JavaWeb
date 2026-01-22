public class StringDemo3
{
    public static void main(String[] args) {
        String str = "2135";
        String result = mathToFination(str);
        System.out.print(result);

    }
    //以下的整合，将数字变成金融表达的数字
    public static String mathToFination(String str)
    {
        String result = "";
        result = mathToSeven(str);
        result = mathToChinese(result);
        result = sevenFull(result);
        return result;
    }
    //把数字补齐到七位数
    public static String mathToSeven(String str)
    {
        String result = "";
        if (str.length() > 7)
            return result;
        int cnt = 7 - str.length();
        for (int i = 0; i < cnt; i++)
        {
            result += '0';
        }
        result += str;
        return result;
    }
    //把数字变为大写的数字
    public static String mathToChinese(String math)
    {
        String result = "";
        char[]chinese = {'零','壹','贰','叁','肆','伍','陆','柒','捌','玖'};
        for (int i = 0; i < math.length(); i++)
        {
            int num = math.charAt(i) - '0';
            result += chinese[num];
        }
        return result;
    }
    //将大写的数字中间加上金融字符千百万
    public static String sevenFull(String str)
    {
        String result = "";
        String unit = "佰拾万仟佰拾元";
        for (int i = 0; i < str.length(); i++) {
            result += str.charAt(i);
            result += unit.charAt(i);
        }
        return result;
    }
}