package t3最长字串;

import javax.print.DocFlavor;
import java.util.HashSet;

//    给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
//    示例 1:
//    输入: s = "abcabcbb"
//    输出: 3
//    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
public class t3_2 {
    public static void main(String[] args) {

    }
    //滑动窗口模型
    //left作为寻找条件,一旦不符合,才进行右移动
    //right作为遍历
    //最后更新最大长度
    //且满足条件的right-left+1就是当前一次查找的最大长度

    //完全正确!!
    public static int lengthOfLongestSubstring(String s) {
        int max=0;
        int left=0;
        HashSet<Character>set=new HashSet<>();
        for (int right = 0; right < s.length(); right++) {
            while(set.contains(s.charAt(right))){
                set.remove(s.charAt(left++));
            }
            set.add(s.charAt(right));
            max = Math.max(max,right-left+1);
        }
        return  max;
    }
}
