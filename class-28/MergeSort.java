import java.util.*;

public class MergeSort {
    public static void main(String[] args) {
        int[] toBeSorted = new int[]{5,2,7,12,6,4,9,3,1,-5};
        System.out.println(Arrays.toString(mergeSort(toBeSorted)));
    }

    public static int[] mergeSort(int[] input) {
        if (input.length < 2) {
            return input;
        } else {
            return merge(
                mergeSort(Arrays.copyOfRange(input, 0, input.length / 2)),
                mergeSort(Arrays.copyOfRange(input, input.length / 2, input.length)),
                input
            );
        }
    }

    public static int[] merge(int[] arr1, int[] arr2, int[] ans) {
        int i = 0;
        int j = 0;
        while (i + j < ans.length) {
            if (j == arr2.length || (i < arr1.length && arr1[i] < arr2[j])) {
                ans[i + j] = arr1[i];
                i++;
            } else {
                ans[i + j]  = arr2[j];
                j++;
            }
        }
        return ans;
    }
}
