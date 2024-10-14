/*
 * An array is bitonic if it consists of an increasing se-
quence of keys followed immediately by a decreasing sequence of keys. Given a
bitonic array, design a logarithmic algorithm to ﬁnd the index of a maximum key.
 */

import lib.StdOut;
import lib.BinarySearch;

public class BitonicArray {
    public static int findMaximumKey(int[] a) {
        if (a == null || a.length == 0) {
            return -1; // Invalid input
        }
        return findMaximumKey(a, 0, a.length - 1);
    }
    
    private static int findMaximumKey(int[] a, int left, int right) {
        // Base cases
        if (left == right) {
            return left; // Only one element
        }
        if (right == left + 1) {
            return a[left] > a[right] ? left : right; // Two elements
        }
        
        int middle = left + (right - left) / 2;
        
        // Check if middle is the peak
        if (a[middle] > a[middle - 1] && a[middle] > a[middle + 1]) {
            return middle;
        }
        
        // Recurse on the appropriate half
        if (a[middle] > a[middle - 1]) {
            return findMaximumKey(a, middle + 1, right); // Peak is on the right side
        } else {
            return findMaximumKey(a, left, middle - 1); // Peak is on the left side
        }
    }
    /*
     * Given a bitonic array of n distinct integers, design
a logarithmic-time algorithm to determine whether a given integer is in the array.
     */
    public static boolean searchBitonic(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return false;
        }
    
        int peak = findMaximumKey(arr);
        return BinarySearch.binarySearch(arr, target, 0, peak, true) || 
                BinarySearch.binarySearch(arr, target, peak + 1, arr.length - 1, false);
    }

    public static void main(String[] args) {
        int[] bitonicArray = {1, 3, 8, 12, 4, 2};
        int index = findMaximumKey(bitonicArray);
        StdOut.println("Index of maximum key: " + index); // Output: 3
    }

}