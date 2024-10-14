
import lib.StdOut;
/*
 * Given an integer array of length n, with each value be-
tween 1 and n, write a code fragment to determine whether there are any duplicate
values. You may not use an extra array (but you do not need to preserve the con-
tents of the given array.)
 */
public class Duplicates {
        public static void main(String[] args) {
            int[] arr = {1, 2, 3, 4, 2};  // Example array
            boolean duplicateFound = false;
    
            for (int i = 0; i < arr.length; i++) {
                int index = Math.abs(arr[i]) - 1; // Adjust for 0-based index
    
                if (arr[index] < 0) {
                    duplicateFound = true;
                    break;
                }
    
                arr[index] = -arr[index]; // Mark as visited
            }
    
           StdOut.println("Duplicate found: " + duplicateFound);
    
            // Optional: Restore the array to its original state
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Math.abs(arr[i]);
            }
        }    
}
