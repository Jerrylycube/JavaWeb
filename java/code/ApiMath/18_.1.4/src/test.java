//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class test {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        calculate(2);
        calculate(3);
        calculate(4);
        calculate(5);
        long end = System.currentTimeMillis();
        long ms = end - start;
        System.out.println("消耗了" + ms + "ms");

        System.out.println("以下为System.arraycopy的演示");
        int[] src = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] des = new int[10];
        System.arraycopy(src, 1, des, 3, 5);
        //srcDelete2
        System.arraycopy(src, 2, src, 1, 6);
        src[src.length - 1] = 0;
        for (int i = 0; i < src.length; i++) {
            System.out.print(src[i]);
        }
        System.out.println();
        for (int i = 0; i < des.length; i++) {
            System.out.print(des[i]);
        }
        System.exit(0);
        calculate(6);
    }

    public static int calculate(int BIT) {
        int cnt = 0;
        int sum = 0;
        int start = (int) Math.pow(10, BIT - 1);
        int end = (int) Math.pow(10, BIT);
        for (int i = start; i < end; i++) {
            int[] bit = new int[BIT];
            int temp = i;
            for (int j = 0; j < BIT; j++) {
                bit[j] = temp % 10;
                temp /= 10;
                sum += (int) Math.pow(bit[j], BIT);
            }
            if (sum == i) {
                cnt++;
                System.out.println(sum);
            }
            sum = 0;
        }
        System.out.println(BIT + "位数有" + cnt + "位自幂数");
        return cnt;
    }

}