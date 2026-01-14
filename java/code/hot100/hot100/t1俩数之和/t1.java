package t1俩数之和;

import java.util.Arrays;
import java.util.HashMap;

//给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个整数，并返回它们的数组下标。
//
//你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
//你可以按任意顺序返回答案。
//示例 1：
//输入：nums = [2,7,11,15], target = 9
//输出：[0,1]
//解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
//示例 2：
//
//输入：nums = [3,2,4], target = 6
//输出：[1,2]
//示例 3：
//
//输入：nums = [3,3], target = 6
//输出：[0,1]
public class t1 {
    public static void main(String[] args) {
        int[] test ={3,3};
        System.out.println(Arrays.toString(twoSum02(test,6)));
    }
    //暴力遍历
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i]+nums[j]==target) {
                    result[0]=i;
                    result[1]=j;
                    break;
                }
            }
        }
    return result;
    }
    //哈希MAP寻找,哈希MAP找东西的效率是O(1)
//    题解很棒，最好能说明一下为什么不怕重复。看评论里有很多人提出这个问题，说hash冲突。
//    我在这里解答一下这个问题。
//            1.每次写入时，判断条件 不是当前的key本身存不存在，而是key和 tag 之间的差值存不存在，这一点很重要。
//            2.题目命题说明了一点，假定只有一个解。也就是说重复元素再多都无所谓。
//    case 1：如果有3个或者以上的重复元素，代表这个重复元素不可能是解，所以写入map的时候直接覆盖也无所谓；
//    case2：如果只有两个重复元素，同样的道理，假如这个重复元素是解，那么必定是两个重复元素的和等于tag。这种情况下，
//    当遇到第二个重复元素时，不会写入map，这个时候直接已经取到解了。
//    所以这种方式，不用考虑hash冲突的问题。
    public static int[] twoSum02(int[] nums, int target) {
        HashMap<Integer,Integer>hs = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            Integer j =  target - nums[i];
            if(hs.containsKey(j)) {
                result[0]=i;
                result[1]=hs.get(j);
                break;
            }
            hs.put(nums[i],i);//此处边查找边放数字,否则会出现返回同一个序号的情况,比如[3,3],target=6;
        }
        return result;
    }
//练习1
    //用哈希表的方式,重点是先判断,再放置到表里面
    public static int[] twoSumPra1(int[] nums, int target) {
        int[] result= new int[2];
        HashMap<Integer,Integer>map=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int val = target - nums[i];
            if(map.containsKey(val)){
                result[0]=i;
                result[i]=map.get(val);
            }
            map.put(nums[i],i);
        }
        return result;

    }

}
