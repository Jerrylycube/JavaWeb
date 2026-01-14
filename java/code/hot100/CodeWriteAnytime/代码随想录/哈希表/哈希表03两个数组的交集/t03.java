package 哈希表.哈希表03两个数组的交集;

import java.util.HashSet;
import java.util.Set;

public class t03 {
//    给定两个数组 nums1 和 nums2 ，返回
//    它们的 交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
//
//
//
//    示例 1：
//
//    输入：nums1 = [1,2,2,1], nums2 = [2,2]
//    输出：[2]
    //使用HashSet集合
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1.length==0||nums2.length==0)return new int[0];
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> resultSet = new HashSet<>();

        for(int num:nums1){
            set1.add(num);
        }
        for(int num:nums2){
            if(set1.contains(num)){
                resultSet.add(num);
            }
        }
        int[] result = new int[resultSet.size()];
        int i = 0;
        for (int num : resultSet) {
            result[i++] = num;
        }
        return result;
    }
}
