package t5最长回文子串;

public class t5 {

//    给你一个字符串 s，找到 s 中最长的 回文 子串。
//
//
//
//    示例 1：
//
//    输入：s = "babad"
//    输出："bab"
//    解释："aba" 同样是符合题意的答案。
//    示例 2：
//
//    输入：s = "cbbd"
//    输出："bb"
public static void main(String[] args) {
    String s =" asda";
    s="sasdggg";
    System.out.println(s);
}
    //暴力算法
    public String longestPalindrome(String s) {
        //滑动窗口
        //套路:遍历right,
        //left用于移动收缩找满足条件的
        int[] result =new int[2];
        int lengthMax=0;
        for(int left = 0;left<s.length();left++){
            //substring 是左闭右开的结构
            int right=s.length()-1;
            while(!isPalindrome(s.substring(left,right+1))){
                right--;
            }
            if(lengthMax<right-left+1){
                result[0]=left;
                result[1]=right;
                lengthMax=right-left+1;
            }
        }
        return s.substring(result[0],result[1]+1);
    }


    //对于每个点都只向中间向俩边扩展,来判断是否为回文子串

    public String longestPalindrome02(String s) {
        String result="";
        for(int i =0;i<s.length();i++){
            //回文字串是奇数
            int l=i-1;
            int r=i+1;
            while(l>=0 && r<s.length() && s.charAt(l)==s.charAt(r)){l--;r++;}
            //此处是不满足条件才出来的,所以长度得-2,才是满足条件的
            if(result.length()<r-l-1)result = s.substring(l+1,r);

            //回文子串是偶数
            l=i;
            r=i+1;
            while(l>=0 && r<s.length() && s.charAt(l)==s.charAt(r)){l--;r++;}
            if(result.length()<r-l-1)result = s.substring(l+1,r);
        }
        return result;
    }
    public boolean isPalindrome(String s){
        int i = 0;
        int j = s.length()-1;
        while(i<j){
            if(s.charAt(i++)!=s.charAt(j--)){
                return false;
            }
        }
        return true;
    }
}
