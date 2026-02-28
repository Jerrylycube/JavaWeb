package t3最长字串;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class t3 {
    public static void main(String[] args) {

        System.out.println(lengthOfLongestSubstring02("pwwkew"));
    }
//    给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
//    示例 1:
//    输入: s = "abcabcbb"
//    输出: 3
//    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。

    //使用Set,暴力解法,一旦Set有冲突,说明遇到了相同的
    public static int lengthOfLongestSubstring(String s) {
        int result=0;
        for (int i = 0; i < s.length()-1; i++) {
            HashSet<Character>set=new HashSet<Character>();
            int sizeBefore= 0;
            int cnt=0;
            for (int j=i;j<s.length();j++) {
                set.add(s.charAt(j));
                if(set.size()==sizeBefore){
                    result=cnt>=result?cnt:result;
                    break;
                }
                cnt++;
                sizeBefore = set.size();
                result = cnt>=result?cnt:result;
            }
        }
        return result;
    }
    //滑动窗口的办法
    //左指针不动,右指针遍历,将左右指针之间的字母加入到Set中
    // 一旦右指针遇到不满足条件的情况,即重复了,要先把Set中对应左指针的字母移除,再移动左指针,直到满足条件
    //每次满足条件都继续加入到Set,
    //且计算当前Set表的大小与最终结果,此时Set表的大小就是(右指针-左指针+1),即滑动窗口的长度
    //不过这样,在找新的左指针的时候会浪费多余的时间,因为是一个一个找的
    //可以使用MAP,直接找到start位置
    public  static int lengthOfLongestSubstring02(String s) {
        // 哈希集合，记录每个字符是否出现过
        int result = 0;
        int n = s.length();
        int i = 0;
        HashSet set=new HashSet<>();
        for (int j = 0; j <n; j++) {
            char tempS = s.charAt(j);
            while(set.contains(tempS)){
				set.remove(s.charAt(i));
				i++;
			}
            set.add(tempS);
            result = Math.max(j-i+1,result);
        }
        return  result;
    }
    public static int lengthOfLongestSubstring03(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);//此处直接找到
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }
}
