package t1俩数之和;

import java.util.HashMap;
import java.util.Map;

//给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个整数，并返回它们的数组下标。
//
//你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
//你可以按任意顺序返回答案。
//示例 1：
//输入：nums = [2,7,11,15], target = 9
//输出：[0,1]
//解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
//示例 3：
//
//输入：nums = [3,3], target = 6
//输出：[0,1]
public class t1_3 {
    public static void main(String[] args) {

    }
	public int[] twoSum(int[] nums, int target) {
		Map<Integer,Integer> map = new HashMap<>();
		int[] result = new int[2];
		//使用map集合,边判断边遍历放入,因为hashmap查找是o1
		for(int i = 0;i<nums.length;i++){
			int value = target - nums[i];
			//如果集合中存在与之互补的,则直接返回
			if(map.containsKey(value)){
				result[0] = i;
				result[1] = map.get(value);
				return result;
			}
			map.put(nums[i],i);
		}
		return result;
	}
}
