package 字符串.字符串04单词反转;

public class t04 {
    class Solution {
        public String reverseWords(String s) {
            //此处用正则表达式,代表1个或多个空格,trim()可以去掉俩边的空格
            String[] str = s.trim().split("\\s+");
            StringBuilder sb = new StringBuilder();
            for(int i = str.length-1;i>=0;i--){
                sb.append(str[i]);
                if(i!=0)sb.append(" ");
            }
            return sb.toString();
        }
    }
}
