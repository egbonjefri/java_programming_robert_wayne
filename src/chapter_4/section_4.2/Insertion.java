
import lib.StdArrayIO;
import lib.StdOut;


public class Insertion {
    /**
     * Sorts the array into ascending order using the insertion sort algorithm.
     * @param a The array to be sorted
     */
    public static void sort(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            // Insert a[i] into the sorted part of the array
            for (int j = i; j > 0; j--) {
                printState(a, i, j);  // Print the current state before comparison
                if (a[j] < (a[j-1])) {
                    printTrace(a, j-1, j);
                    exchange(a, j-1, j);
                    
                } else {
                    break; // Element is in the right position, stop inner loop
                }
            }
        }
    }
/*
 * Add methods to Insertion to
support sorting subarrays.
 */

    public static void sortSubArray(int[] arr, int start, int end){
        for (int i = start + 1; i <= end; i++) {
            for (int j = i; j > start && arr[j-1] > arr[j]; j--) {
                exchange(arr, j, j-1);
            }
        }
    }
    private static void printTrace(int[] a, int i, int j) {
        
        for (int k = i; k < j; k++) {
                StdOut.printf("%d %d ", a[i], a[j]); 
        }
        StdOut.println();
    }


    private static void printState(int[] a, int i, int j) {
        StdOut.print("i=" + i + " j=" + j + "  ");
        for (int k = 0; k < a.length; k++) {
            if (k == j) {
                StdOut.print("[" + a[k] + "] ");
            } else {
                StdOut.print(a[k] + " ");
            }
        }
        StdOut.println();
    }


    /**
     * Swaps two elements in the array.
     * @param a The array containing the elements
     * @param i Index of the first element
     * @param j Index of the second element
     */
    private static void exchange(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static void main(String[] args) {
        int[] a = {15, 7, 4, 2, 3, 5, 9, 1};
        StdOut.println("Initial array:");
        StdArrayIO.print(a);
        sort(a);
        StdOut.println("Sorted array:");
        StdArrayIO.print(a);
    }
}