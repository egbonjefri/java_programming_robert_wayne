/*
 * Design a linear-time algorithm to sort an
array of Comparable objects that is known to have at most three distinct values.
(Edsger Dijkstra named this the Dutch-national-flag problem because the result is
three “stripes” of values like the three stripes in the ﬂag.)
 */

import java.util.Arrays;
import lib.StdOut;

public class DutchNationalFlagProblem {
    public static <T extends Comparable<T>> void sort(T[] array) {
        if (array.length == 0) return;

        // Identify the three distinct values
        T value1 = array[0];
        T value2 = null;
        T value3 = null;

        // Find the second and third distinct values
        for (T element : array) {
            if (!element.equals(value1)) {
                if (value2 == null) {
                    value2 = element;
                } else if (!element.equals(value2)) {
                    value3 = element;
                    break;
                }
            }
        }

        // If there are less than three distinct values, the array is already sorted
        if (value2 == null || value3 == null) return;

        // Ensure value1 < value2 < value3
        if (value1.compareTo(value2) > 0) {
            T temp = value1;
            value1 = value2;
            value2 = temp;
        }
        if (value1.compareTo(value3) > 0) {
            T temp = value1;
            value1 = value3;
            value3 = temp;
        }
        if (value2.compareTo(value3) > 0) {
            T temp = value2;
            value2 = value3;
            value3 = temp;
        }

        // Three-way partitioning
        int low = 0, mid = 0, high = array.length - 1;

        while (mid <= high) {
            if (array[mid].equals(value1)) {
                swap(array, low++, mid++);
            } else if (array[mid].equals(value2)) {
                mid++;
            } else {
                swap(array, mid, high--);
            }
        }
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] array = {3, 2, 1, 3, 2, 1, 2, 1, 3};
        StdOut.println("Original array: " + Arrays.toString(array));
        sort(array);
        StdOut.println("Sorted array: " + Arrays.toString(array));

        String[] strArray = {"orange", "apple", "banana", "orange", "banana", "apple", "banana"};
        StdOut.println("Original array: " + Arrays.toString(strArray));
        sort(strArray);
        StdOut.println("Sorted array: " + Arrays.toString(strArray));
    }
}
