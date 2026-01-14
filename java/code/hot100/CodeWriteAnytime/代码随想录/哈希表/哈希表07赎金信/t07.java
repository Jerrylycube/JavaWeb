package 哈希表.哈希表07赎金信;
//给你两个字符串：ransomNote 和 magazine ，
// 判断 ransomNote 能不能由 magazine 里面的字符构成。
//
//如果可以，返回 true ；否则返回 false 。
//
//magazine 中的每个字符只能在 ransomNote 中使用一次。
public class t07 {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] nums = new int[26];
        if(magazine.length()<ransomNote.length())return false;
        for(int i = 0;i<magazine.length();i++){
            char cm = magazine.charAt(i);
            nums[cm-'a']++;
        }
        for(int i = 0;i < ransomNote.length();i++){
            char cr = ransomNote.charAt(i);
            nums[cr-'a']--;
        }
        for(int num:nums){
            if(num<0){
                return false;
            }
        }
        return true;
    }
}
