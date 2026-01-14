package 数组.数组05长度最小的子数组;

public class t5 {
    public static void main(String[] args) {

    }
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0 ;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for(int right = 0;right<nums.length;right++){
            sum+=nums[right];
            while(sum>=target){
                min = Math.min(min,right-left+1);
                sum-=nums[left];
                left++;
            }
        }
        return min==Integer.MAX_VALUE?0:min;
    }
}
