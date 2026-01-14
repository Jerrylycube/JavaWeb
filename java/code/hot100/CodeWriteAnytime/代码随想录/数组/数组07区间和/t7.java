package 数组.数组07区间和;
import  java.util.Scanner;
public class t7 {
    //    题目描述
//    给定一个整数数组 Array，请计算该数组在每个指定区间内元素的总和。
//    输入描述
//    第一行输入为整数数组 Array 的长度 n，接下来 n 行，每行一个整数，表示数组的元素。
//    随后的输入为需要计算总和的区间，直至文件结束。
//    输出描述
//    输出每个指定区间内元素的总和。
//    输入示例
//        5
//        1
//        2
//        3
//        4
//        5
//        0 1
//        1 3
//    输出示例
//3 9
    //    ACM模式,输入输出,和导包都要自己来
    //此题用暴力方法,容易超时,需要使用前缀和的方式,
    // 即先将,从0到n之间的每个子区间都求一次和,之后可以直接用这些结果,用空间换时间
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] num = new int[n];
        int[] sumEvery = new int[n];
        int preSum = 0;
        for (int i = 0; i < n; i++) {
            num[i]=input.nextInt();
            preSum+=num[i];
            sumEvery[i] = preSum;
        }
        while(input.hasNextInt()){
            int n1 = input.nextInt();
            int n2 = input.nextInt();
            int sum = 0;
            if(n1==0){
                sum = sumEvery[n2];
            }else {
                sum = sumEvery[n2] - sumEvery[n1 - 1];
            }
            System.out.println(sum);
        }
        input.close();
    }

}
