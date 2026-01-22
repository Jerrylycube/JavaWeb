public class StringDemo2{
    public static void main(String[] args) {
        //将数组的数字拼接成字符串
        int[] num = { 1, 2, 3 };
        String str = arrToStr(num);
        System.out.print(str);
        System.out.println();
        //将字符串反转
        String strback = strConver(str);
        System.out.print(strback);
        System.out.println();
    }
    //将数组的数字拼接成字符串
    public static String arrToStr(int[]num)
    {
        String str = new String();
        if (num == null) {
            System.out.println("数组为空");
            str = "null";
            return str;
        } else if (num.length == 0) {
            str = "";
            return str;
        } else {
            str = "[";
            for (int idx = 0; idx < num.length; idx++) {
                if (idx != num.length - 1) {
                    str = str + num[idx] + ",";
                } else {
                    str = str + num[idx] + "]";
                }
            }
        }
        return str;
    }
    //将字符串反转
    public static String strConver(String str)
    {
        String strBack = "";
        for (int j = str.length() - 1; j >= 0; j--)
        {
            strBack += str.charAt(j);
        }

        return strBack;
    }
    
  
}

