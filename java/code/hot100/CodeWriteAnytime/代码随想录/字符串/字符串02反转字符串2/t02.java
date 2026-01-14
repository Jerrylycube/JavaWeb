package 字符串.字符串02反转字符串2;

//给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
//
//如果剩余字符少于 k 个，则将剩余字符全部反转。
//如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
//
//
//示例 1：
//
//输入：s = "abcdefg", k = 2
//输出："bacdfeg"

public class t02 {
    public String reverseStr(String s, int k) {
        System.out.println();
        if(k==1||s.length()==1)return s;
        char[] c = s.toCharArray();
        int i=0;
        while(true){
            if((s.length()-i)>=k){
                reverseString(c,i,i+k);
                i+=2*k;
                if(i>=s.length()){break;}
            }
            else{
                reverseString(c,i,s.length());
                break;
            }
        }
        return new String(c);
    }
    public void reverseString(char[] s,int start,int end) {
        int i = start;
        int j = end-1;
        while(i<j){
            char temp = s[i];
            s[i]=s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}
