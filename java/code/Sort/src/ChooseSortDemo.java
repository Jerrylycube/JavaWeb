import java.util.Arrays;

public class ChooseSortDemo {
    public static void main(String[] args) {
        int[] arr = {1, 1, 6, 2, 7, 9, 3, 4, 5, 1, 10, 8};
        ChooseSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void ChooseSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
