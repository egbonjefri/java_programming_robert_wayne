/*
 * Design a linear-time algorithm to sort an array of Comparable objects 
 * that is known to have at most two distinct values. 
 * Hint : Maintain two pointers, one starting at the left end and moving right, 
 * and the other starting at the right end and moving left. 
 * Maintain the invariant that all elements to the left
 * of the left pointer are equal to the smaller of the two values and all elements to the
 * right of the right pointer are equal to the larger of the two values.
 */

import java.util.Arrays;
import lib.StdOut;

public class Partitioning {
        public static <T extends Comparable<T>> void sort(T[] array) {
            if (array.length == 0) return;

            // Identify the two distinct values
            T value1 = array[0];
            T value2 = null;

            // Find the second distinct value (if it exists)
            for (T element : array) {
            if (!element.equals(value1)) {
                value2 = element;
                break;
                 }
            }
            // If there is no second distinct value, the array is already sorted
            if (value2 == null) return;

            // Ensure value1 is the smaller and value2 is the larger
            if (value1.compareTo(value2) > 0) {
                T temp = value1;
                value1 = value2;
                value2 = temp;
            }
            // Two-pointer approach
            int left = 0;
            int right = array.length - 1;
            while (left <= right) {
                if (array[left].equals(value1)) {
                    left++;
                } else if (array[right].equals(value2)) {
                    right--;
                } else {
                    // Swap elements
                    T temp = array[left];
                    array[left] = array[right];
                    array[right] = temp;
                    left++;
                    right--;
                }
            }
        }

        public static void main(String[] args) {
            Integer[] array = {2, 1, 2, 1, 2, 1, 1};
            StdOut.println("Original array: " + Arrays.toString(array));
            sort(array);
            StdOut.println("Sorted array: " + Arrays.toString(array));
    
            String[] strArray = {"orange", "apple", "orange", "apple", "orange"};
            StdOut.println("Original array: " + Arrays.toString(strArray));
            sort(strArray);
            StdOut.println("Sorted array: " + Arrays.toString(strArray));
        }
}
