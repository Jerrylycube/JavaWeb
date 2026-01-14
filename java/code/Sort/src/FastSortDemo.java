import java.util.Arrays;

public class FastSortDemo {
    public static void main(String[] args) {
        int[] arr = {1, 1, 6, 2, 7, 9, 3, 4, 5, 1, 10, 8};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int i, int j) {
        //记录比较范围
        int start = i;
        int end = j;
        if (start > end) return;
        //记录基准值
        int key = arr[start];
        while (start != end) {
            //从末尾开始比较基准，找到小于基准的
            while (true) {
                if (end <= start || arr[end] < key) {
                    break;
                }
                end--;
            }
            //再从头开始比较基准，找到大于基准的
            while (true) {
                if (end <= start || arr[start] > key) {
                    break;
                }
                start++;
            }
            //找到双方的，就进行交换，直到俩个头和末尾重合
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }
        //当交换完后，重合的位置就基准值的位置
        int temp = arr[i];
        arr[i] = arr[start];
        arr[start] = temp;

        quickSort(arr, i, start - 1);
        quickSort(arr, start + 1, j);
    }
}
