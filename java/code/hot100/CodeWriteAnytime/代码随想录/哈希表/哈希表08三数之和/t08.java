package 哈希表.哈希表08三数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class t08 {
    public List<List<Integer>> threeSum(int[] nums) {
        //双指针法
// 拿这个nums数组来举例，首先将数组排序，然后有一层for循环，i从下标0的地方开始，同时定一个下标left 定义在i+1的位置上，定义下标right 在数组结尾的位置上。

// 依然还是在数组中找到 abc 使得a + b +c =0，我们这里相当于 a = nums[i]，b = nums[left]，c = nums[right]。

// 接下来如何移动left 和right呢， 如果nums[i] + nums[left] + nums[right] > 0 就说明 此时三数之和大了，因为数组是排序后了，所以right下标就应该向左移动，这样才能让三数之和小一些。

// 如果 nums[i] + nums[left] + nums[right] < 0 说明 此时 三数之和小了，left 就向右移动，才能让三数之和大一些，直到left与right相遇为止。

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        // a = nums[i], b = nums[left], c = nums[right]
        for(int i=0;i<nums.length;i++){
            //只要最左边的元素大于0了,就说明不可能了
            if(nums[i]>0)return result;
            //对a去重,因为这个我们是对于a遍历整个数组的,一旦前面用过,那么,这个数字就不能用了,直接下一个循环
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int left = i+1;
            int right = nums.length - 1;
            while(left<right)
                if(nums[left]+nums[right]+nums[i]>0){
                    right--;
                }else if(nums[left]+nums[right]+nums[i]<0){
                    left++;
                }else{
                    List<Integer> list = new ArrayList<>();
                    Collections.addAll(list,nums[left],nums[right],nums[i]);
                    result.add(list);
                    //对重复bc的进行排除
                    while(left<right && nums[right]==nums[right-1])right--;
                    while(left<right && nums[left]==nums[left+1])left++;
                    left++;
                    right--;
                }
        }
        return result;
    }
}
