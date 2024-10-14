/*
 * Given a sorted array of Comparable items, write func-
tions floor() and ceiling() that return the index of the largest (or smallest) item
not larger (or smaller) than an argument item in logarithmic time.
 */

import lib.StdOut;
/*
 * These methods work efficiently in logarithmic time due to the binary search algorithm.
 */

public class FloorAndCeiling {
        // Method to find the floor index
        /*
         * This method finds the largest element in the array
         *  that is less than or equal to the given key.
         */
        public static int floor(int[] arr, int key) {
            int low = 0;
            int high = arr.length - 1;
            int floorIndex = -1;
    
            while (low <= high) {
                int mid = low + (high - low) / 2;
    
                if (arr[mid] == key) {
                    return mid;
                } else if (arr[mid] < key) {
                    floorIndex = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
    
            return floorIndex;
        }
    /*
     * This method finds the smallest element in the array
     *  that is greater than or equal to the given key.
     */
        // Method to find the ceiling index
        public static int ceiling(int[] arr, int key) {
            int low = 0;
            int high = arr.length - 1;
            int ceilingIndex = -1;
    
            while (low <= high) {
                int mid = low + (high - low) / 2;
    
                if (arr[mid] == key) {
                    return mid;
                } else if (arr[mid] < key) {
                    low = mid + 1;
                } else {
                    ceilingIndex = mid;
                    high = mid - 1;
                }
            }
    
            return ceilingIndex;
        }
    
        // Main method for testing
        public static void main(String[] args) {
            int[] arr = {1, 2, 4, 6, 8, 10};
            int key = 5;
    
            StdOut.println("Floor index of " + key + ": " + floor(arr, key));  // Output: 2 (arr[2] = 4)
            StdOut.println("Ceiling index of " + key + ": " + ceiling(arr, key));  // Output: 3 (arr[3] = 6)
        }
}
