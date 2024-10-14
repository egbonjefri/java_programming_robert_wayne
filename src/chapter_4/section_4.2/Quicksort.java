/*
 * Write a recursive program that sorts an array of Comparable
objects by using, as a subroutine, the partitioning algorithm described in the pre-
vious exercise: First, pick a random element v as the partitioning element. Next,
partition the array into a left subarray containing all elements less than v, followed
by a middle subarray containing all elements equal to v, followed by a right subar-
ray containing all elements greater than v. Finally, recursively sort the left and right
subarrays.
 */


import lib.StdRandom;


public class Quicksort {

    public static <T extends Comparable<T>> void sort(T[] array) {
        threeWayQuickSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void threeWayQuickSort(T[] array, int low, int high) {
        if (low >= high) return;

        // Choose a random pivot and swap with the first element
        int pivotIndex = low + StdRandom.uniformInt(high - low + 1);
        T pivot = array[pivotIndex];
        swap(array, low, pivotIndex);

        int lt = low, gt = high;
        int i = low + 1;

        while (i <= gt) {
            int cmp = array[i].compareTo(pivot);
            if (cmp < 0) {
                swap(array, lt++, i++);
            } else if (cmp > 0) {
                swap(array, i, gt--);
            } else {
                i++;
            }
        }

        // Recursively sort the left and right subarrays
        threeWayQuickSort(array, low, lt - 1);
        threeWayQuickSort(array, gt + 1, high);
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
