package 数组.数组06螺旋序列;

public class t6 {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int offset = 1;//保证左闭右开的一个偏移量
        int startX = 0;//每圈的起点X
        int startY = 0;//每圈的起点Y
        int cnt = 1;//当前的需要填入数字
        int loop = 1;//圈数
        int i ,j;//代表当前的点
        //奇数的圈数是loop< n/2,单独处理最后一个点,即循环结束的拐点
        //偶数的圈数就是loop < n/2
        while(loop <= n/2)
        {
            //处理从左往右
            for(j=startY;j<n-offset;j++){
                result[startX][j]=cnt;
                cnt++;
            }
            //处理从上往下
            for(i=startX;i<n-offset;i++){
                result[i][j]=cnt;
                cnt++;
            }
            //处理从右往左
            for(;j>startY;j--){
                result[i][j]=cnt;
                cnt++;
            }
            //处理从下往上
            for(;i>startX;i--){
                result[i][j]=cnt;
                cnt++;
            }
            loop++;
            startX++;
            startY++;
            offset++;
        }
        //单独处理奇数的最后一个点
        if(n%2==1){
            result[startX][startY]=cnt;
        }
        return result;
    }
}
