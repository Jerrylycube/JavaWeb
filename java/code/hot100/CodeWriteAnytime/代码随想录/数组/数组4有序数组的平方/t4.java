package 数组.数组4有序数组的平方;

public class t4 {
    public static void main(String[] args) {

    }
//    给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
//    示例 1：
//
//    输入：nums = [-4,-1,0,3,10]
//    输出：[0,1,9,16,100]
//    解释：平方后，数组变为 [16,1,0,9,100]
//    排序后，数组变为 [0,1,9,16,100]
//    示例 2：
//
//    输入：nums = [-7,-3,2,3,11]
//    输出：[4,9,9,49,121]
    //双指针的思路
    //当前数组最大值,只可能在数组俩边,不可能在中间,就可以只比较俩端,从俩头遍历
    public int[] sortedSquares(int[] nums) {
        int result[]=new int[nums.length];
        int left =0;
        int right = nums.length-1;
        int temp = result.length-1;
        while(left<=right){
            if(nums[left]*nums[left]<=nums[right]*nums[right]){
                result[temp--]=nums[right]*nums[right];
                right--;
            }
            else {
                result[temp--]=nums[left]*nums[left];
                left++;
            }
        }
        return  result;
    }
}
