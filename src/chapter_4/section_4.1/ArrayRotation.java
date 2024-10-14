/*
 * Given an array of n elements, give a linear-time algorithm
to rotate the string k positions. That is, if the array contains a_0, a_1, …, a_n-1 , the
rotated array is a_k, a_k+1, …, a_n-1, a_0, …, a_k-1. Use at most a constant amount of extra
memory. Hint : Reverse three subarrays.
 */

import lib.StdOut;
import lib.Stopwatch;
import generators.RandomArrayGenerator;

public class ArrayRotation {
    public static void rotate(int[] array, int k) {
        int n = array.length;
        k = k % n;  // To handle cases where k >= n

        reverse(array, 0, n - 1);
        reverse(array, 0, n - k - 1);
        reverse(array, n - k, n - 1);
    }

    private static void reverse(int[] array, int start, int end) {
        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int[] array1 = RandomArrayGenerator.generateIntArray(n);
        int k = 1;
        Stopwatch timer = new Stopwatch();
        rotate(array1, k);
        double timer1 = timer.elapsedTime();

        // next we double the size of the input to determine T_N / T_N/2
        int[] array2 =  RandomArrayGenerator.generateIntArray(n * 2);
        Stopwatch timer_2 = new Stopwatch();
        rotate(array2, k);
        double timer2 = timer_2.elapsedTime();
        if(timer1 == 0){
            StdOut.println("The running time of this algorithm is: " + (timer2 - timer1));
            return;
        }
        StdOut.println("The time complexity of this program is approximately: O(n^" + (Math.log(timer2 / timer1) / Math.log(2)) + ")");
    }
}
