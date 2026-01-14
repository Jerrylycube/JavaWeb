package 字符串.字符串05右旋字符串;

import java.util.Scanner;
public class t05{
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine(); // 消耗换行符
        String str = input.nextLine();
        char[] c = str.toCharArray();
        reverseString(c,0,str.length()-n);
        reverseString(c,str.length()-n,str.length());
        reverseString(c,0,str.length());
        System.out.println(new String(c));
    }
    public static void reverseString(char[] s,int start,int end) {
        int i = start;
        int j = end-1;
        while(i<j){
            char temp = s[i];
            s[i]=s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}
