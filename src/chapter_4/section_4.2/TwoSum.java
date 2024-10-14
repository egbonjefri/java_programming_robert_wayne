/*
 * Given an array of n integers, design a linearithmic-time algo-
rithm to determine whether any two of them sum to 0.
 */

 /*
  * Sort the array: Sorting an array takes 𝑂(𝑛log𝑛) time.
  * Use the two-pointer technique: After sorting, use two pointers to find if there exist two numbers that sum to 0. 
  * This technique will run in 𝑂(𝑛)time.
  */

import lib.Merge;
import lib.StdOut;


public class TwoSum {
    public static boolean hasTwoSumToZero(int[] array) {
        Merge.sort(array);  
        
        int left = 0;
        int right = array.length - 1;
        
        while (left < right) {
            int sum = array[left] + array[right];
            
            if (sum == 0) {
                return true;
            } else if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        int[] array = {3, -1, 4, -3, 5, 9, -2};
        StdOut.println(hasTwoSumToZero(array));  
    }
}
