
import lib.StdArrayIO;
import java.util.Arrays;
public class Merge {

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int leftIndex = 0, rightIndex = 0, resultIndex = 0;
    
        // While both arrays have elements
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                result[resultIndex++] = left[leftIndex++];
            } else {
                result[resultIndex++] = right[rightIndex++];
            }
        }
    
        // If there are remaining elements in left array
        while (leftIndex < left.length) {
            result[resultIndex++] = left[leftIndex++];
        }
    
        // If there are remaining elements in right array
        while (rightIndex < right.length) {
            result[resultIndex++] = right[rightIndex++];
        }
    
        return result;
    }
    /*
     * Add methods to Merge  to
support sorting subarrays.
     */
    public static int[] sortSubarray(int[] a, int start, int end) {
        if (end - start <= 1) {
            return new int[]{a[start]};
        }
        int middle = start + (end - start) / 2;
        int[] left = new int[middle - start];
        int[] right = new int[end - middle];

        // Populate left and right arrays
        for (int i = start; i < middle; i++) {
            left[i - start] = a[i];
        }
        for (int i = middle; i < end; i++) {
            right[i - middle] = a[i];
        }

        left = sortSubarray(left, 0, left.length);
        right = sortSubarray(right, 0, right.length);

        return merge(left, right);
    }

    public static int[] sort(int[] a) {
        return sortSubarray(a, 0, a.length);
    }
/*
 * Develop a nonrecursive version of mergesort (PROGRAM 4.2.6). For sim-
plicity, assume that the number of items n is a power of 2. Extra credit: Make your
program work even if n is not a power of 2
 */
    public static void mergeSort(int[] arr) {
        int n = arr.length;
        
        // Start with subarrays of size 1, then 2, 4, 8, and so on
        for (int size = 1; size < n; size *= 2) {
            for (int start = 0; start < n; start += 2 * size) {
                int mid = Math.min(start + size, n);
                int end = Math.min(start + 2 * size, n);
                
                int[] left = Arrays.copyOfRange(arr, start, mid);
                int[] right = Arrays.copyOfRange(arr, mid, end);
                
                int[] merged = merge(left, right);
                System.arraycopy(merged, 0, arr, start, merged.length);
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {15, 1, 7, 4, 2, 3, 5, 9, 1};
        a = sort(a);
        StdArrayIO.print(a);
    }
}
