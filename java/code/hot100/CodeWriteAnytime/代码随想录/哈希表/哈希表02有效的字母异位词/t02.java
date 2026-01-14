package 哈希表.哈希表02有效的字母异位词;

import java.util.HashMap;
import java.util.Map;

public class t02 {


    //直接使用数组,s有对应的字母,就在其AscII表的顺序上+1,t有对应的就-1,最后看数组是不是全0,全0就成功
    public boolean isAnagram02(String s, String t) {
        if(s.length()!=t.length())return false;
        int[] num = new int[26];

        for(int i =0 ;i< s.length();i++){
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            num[(int)(cs-'a')]++;
            num[(int)(ct-'a')]--;
        }
        for(int i:num){
            if(i!=0)return false;
        }
        return true;
    }
    //哈希map
    public boolean isAnagram(String s, String t) {
        HashMap<Character,Integer> map1 = new HashMap<>();
        HashMap<Character,Integer>map2 = new HashMap<>();
        if(s.length()!=t.length())return false;
        for(int i = 0;i<s.length();i++){
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            if(!map1.containsKey(cs)){
                map1.put(cs,1);
            }
            else{
                int cnt = map1.get(cs);
                map1.put(cs,++cnt);
            }

            if(!map2.containsKey(ct)){
                map2.put(ct,1);
            }
            else{
                int cnt = map2.get(ct);
                map2.put(ct,++cnt);
            }
        }

        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();

            if (!map2.containsKey(key) || !map2.get(key).equals(value)) {
                return false;  // 这里可以正确从方法返回
            }
        }
        return true;

    }
}
