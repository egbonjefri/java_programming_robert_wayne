/*
 * Given an array of n real numbers, design a
logarithmic-time algorithm to identify a local minimum (an index i such that both
a[i] < a[i-1] and a[i] < a[i+1]).
 */

 import lib.StdOut;

public class LocalMinimum {
    public static int findLocalMinimum(double[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty or null");
        }
        
        int n = arr.length;
        
        // Handle edge cases
        if (n == 1 || arr[0] < arr[1]) return 0;
        if (arr[n-1] < arr[n-2]) return n-1;
        
        int left = 1;
        int right = n - 2;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] < arr[mid-1] && arr[mid] < arr[mid+1]) {
                return mid;  // Local minimum found
            } else if (arr[mid-1] < arr[mid]) {
                right = mid - 1;  // Search left half
            } else {
                left = mid + 1;  // Search right half
            }
        }
        
        return left;  // This line should never be reached if the input is valid
    }

    public static void main(String[] args) {
        double[] arr = {9, 7, 2, 8, 5, 6, 3, 4};
        int localMinIndex = findLocalMinimum(arr);
        StdOut.println("Local minimum found at index: " + localMinIndex);
        StdOut.println("Local minimum value: " + arr[localMinIndex]);
    }
}