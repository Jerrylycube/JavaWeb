public class Practice1_8 {
    public static void main(String[] args) {
        String str = "Java语言是面向对象的,Java语言是健壮的,Java语言是安全的,Java是高性能的,Java语言是跨平台的";
        int cnt = containNum(str);
        System.out.print(cnt);
    }

    public static int containNum(String str)
    {
        int cnt = 0;
        int contain = 0;
        while (true)
        {
            contain = str.indexOf("Java");
            str = str.substring(contain + 4);
            if (contain < 0)
                break;
            cnt++;
        }
        return cnt;
    }
}