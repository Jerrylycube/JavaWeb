package t3最长字串;

import java.util.HashSet;

//    给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
//    示例 1:
//    输入: s = "abcabcbb"
//    输出: 3
//    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
public class t3_1 {
    public static void main(String[] args) {

    }
    //使用Set集合,滑动窗口
    //left用于,查找重复,找到重复,要移动left,直到没有
    //right用于遍历

    //错误,不用使用计数器计算长度了
    //长度就是right-left+1
    public static int lengthOfLongestSubstring(String s) {
        int length = 0;
        int left=0,right=0;
        HashSet<Character>set =new HashSet<>();
        for (right=0;right<s.length();right++){
            //int cnt=0;
            char tempR = s.charAt(right);
            while(set.contains(tempR))
            {
                set.remove( s.charAt(left));
                left++;
                //cnt--;
            }
            set.add(tempR);
            //cnt++;

            length =  Math.max(length,right-left+1);
        }
        return length;
    }
}
