package 数组.数组2二分查找;
//704
public class t2 {
    public static void main(String[] args) {

    }
    public int search(int[] nums, int target) {
        int result=-1;
        int left =0;int right=nums.length-1;
        while(left<=right){
            int min = (left+right)/2;
            if(target>nums[min]){
                left = min+1;
            } else if (target<nums[min]) {
                right = min -1;
            }
            else {
                result = min;
                break;
            }
        }
        return result;
    }
}
