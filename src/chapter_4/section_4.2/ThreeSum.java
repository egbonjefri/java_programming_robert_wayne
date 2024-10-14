/*
 * Given an array of n integers, design an algorithm to determine
whether any three of them sum to 0. The order of growth of the running time of
your program should be n2 log n. Extra credit: Develop a program that solves the
problem in quadratic time.
 */

import lib.Merge;
import lib.StdOut;


public class ThreeSum {
    public static boolean hasThreeSumToZero(int[] array) {
        Merge.sort(array); 
        
        for (int i = 0; i < array.length - 2; i++) {
            if (i > 0 && array[i] == array[i - 1]) {
                continue;  // Skip duplicates
            }
            
            int left = i + 1;
            int right = array.length - 1;
            
            while (left < right) {
                int sum = array[i] + array[left] + array[right];
                
                if (sum == 0) {
                    return true;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        int[] array = {3, -1, 4, -3, 1, 2, -2};
        StdOut.println(hasThreeSumToZero(array)); 
    }  
}
