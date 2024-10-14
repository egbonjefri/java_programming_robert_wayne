/******************************************************************************
 *  Compilation:  javac Merge.java
 *  Execution:    java Merge < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/22mergesort/tiny.txt
 *                https://algs4.cs.princeton.edu/22mergesort/words3.txt
 *
 *  Sorts a sequence of strings from standard input using mergesort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Merge < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java Merge < words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *
 ******************************************************************************/

/**
 *  The {@code Merge} class provides static methods for sorting an
 *  array using a top-down, recursive version of <em>mergesort</em>.
 *  <p>
 *  This implementation takes &Theta;(<em>n</em> log <em>n</em>) time
 *  to sort any array of length <em>n</em> (assuming comparisons
 *  take constant time). It makes between
 *  ~ &frac12; <em>n</em> log<sub>2</sub> <em>n</em> and
 *  ~ 1 <em>n</em> log<sub>2</sub> <em>n</em> compares.
 *  <p>
 *  This sorting algorithm is stable.
 *  It uses &Theta;(<em>n</em>) extra memory (not including the input array).
 *  <p>
 *  For additional documentation, see
 *  <a href="https://algs4.cs.princeton.edu/22mergesort">Section 2.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *  For an optimized version, see {@link MergeX}.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

package lib;

import java.util.Arrays;

public class Merge {

    // This class should not be instantiated.
    private Merge() { }

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        // ... (merge method remains unchanged)
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static <T extends Comparable<T>> void sort(T[] a, T[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static <T extends Comparable<T>> void sort(T[] a) {
        T[] aux = Arrays.copyOf(a, a.length);
        sort(a, aux, 0, a.length - 1);
        assert isSorted(a);
    }



    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int leftIndex = 0, rightIndex = 0, resultIndex = 0;
    
        // While both arrays have elements
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                result[resultIndex++] = left[leftIndex++];
            } else {
                result[resultIndex++] = right[rightIndex++];
            }
        }
    
        // If there are remaining elements in left array
        while (leftIndex < left.length) {
            result[resultIndex++] = left[leftIndex++];
        }
    
        // If there are remaining elements in right array
        while (rightIndex < right.length) {
            result[resultIndex++] = right[rightIndex++];
        }
    
        return result;
    }
    /*
     * Add methods to Merge  to
support sorting subarrays.
     */
    public static int[] sortSubarray(int[] a, int start, int end) {
        if (end - start <= 1) {
            return new int[]{a[start]};
        }
        int middle = start + (end - start) / 2;
        int[] left = new int[middle - start];
        int[] right = new int[end - middle];

        // Populate left and right arrays
        for (int i = start; i < middle; i++) {
            left[i - start] = a[i];
        }
        for (int i = middle; i < end; i++) {
            right[i - middle] = a[i];
        }

        left = sortSubarray(left, 0, left.length);
        right = sortSubarray(right, 0, right.length);

        return merge(left, right);
    }

    public static int[] sort(int[] a) {
        return sortSubarray(a, 0, a.length);
    }
    /***************************************************************************
    *  Helper sorting function.
    ***************************************************************************/

    // is v < w ?
    private static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/
    private static <T extends Comparable<T>> boolean isSorted(T[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> boolean isSorted(T[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    // print array to standard output
    private static <T> void show(T[] a) {
        for (T item : a) {
            StdOut.println(item);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; mergesorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        String[] a = StdIn.readAllStrings();
        Merge.sort(a);
        show(a);
    }
}
