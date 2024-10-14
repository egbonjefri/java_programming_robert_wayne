/*
 * Write a program SubsetSum that reads long integers from
standard input, and counts the number of subsets of those integers that sum to
exactly zero. Give the order of growth of the running time of your program
 */

import lib.StdIn;
import lib.StdOut;

public class SubsetSum {
    public static int countSubsets(long[] nums, int index, long sum) {
        if (index == nums.length) {
            return sum == 0 ? 1 : 0;
        }

        // Include the current element
        int include = countSubsets(nums, index + 1, sum + nums[index]);

        // Exclude the current element
        int exclude = countSubsets(nums, index + 1, sum);

        return include + exclude;
    }

    public static void main(String[] args){
        long[] nums = StdIn.readAllLongs();
        int result = countSubsets(nums, 0, 0) - 1; // Exclude the empty set
        StdOut.println("The subsets are: " + result);

    }
    
}


/*
 *To determine the order of growth of this method, let's analyze its behavior:

1. The function makes two recursive calls for each element in the array.
2. This process continues until it reaches the end of the array.
3. At each step, it's essentially creating a binary tree of recursive calls.

Given these characteristics, we can deduce the following:



1. Time Complexity:
   - For an array of length n, the function will make 2 calls for each element.
   - This results in a total of 2^n function calls.
   - Therefore, the time complexity is O(2^n).

2. Space Complexity:
   - The maximum depth of the recursion tree is n (the length of the array).
   - At each level, we're using a constant amount of additional space.
   - Thus, the space complexity due to the recursion stack is O(n).

 */