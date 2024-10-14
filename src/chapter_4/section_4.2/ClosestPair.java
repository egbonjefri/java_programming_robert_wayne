/*
 * Given an array of n real numbers, design a linearithmic-time
algorithm to ﬁnd a pair of numbers that are closest in value.
 */
import lib.Merge;
public class ClosestPair {
    
    public static int[] findClosestPair(int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return null;  // Not enough elements to form a pair
        }
        
        // Step 1: Sort the array
        Merge.sort(arr);  // Sorting takes O(n log n)
        
        // Step 2: Find the pair with the smallest difference
        int minDiff = Integer.MAX_VALUE;
        int[] closestPair = new int[2];
        
        for (int i = 1; i < n; i++) {
            int diff = Math.abs(arr[i] - arr[i - 1]);
            if (diff < minDiff) {
                minDiff = diff;
                closestPair[0] = arr[i - 1];
                closestPair[1] = arr[i];
            }
        }
        
        return closestPair;
    }
    
    public static void main(String[] args) {

    }
}