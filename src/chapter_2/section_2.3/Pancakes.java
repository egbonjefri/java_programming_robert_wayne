/*
 * You have a stack of n pancakes of varying sizes on a grid-
dle. Your goal is to rearrange the stack in order so that the largest pancake is on
the bottom and the smallest one is on top. You are only permitted to ﬂip the top k
pancakes, thereby reversing their order. Devise a recursive scheme to arrange the
pancakes in the proper order that uses at most 2n - 3 ﬂips.
 */
import java.util.Arrays;

public class Pancakes {
    public static void main(String[] args) {
        int[] pancakes = {3, 6, 2, 7, 4, 5, 1};
        pancakeSort(pancakes, pancakes.length);
        System.out.println(Arrays.toString(pancakes));
    }

    // Function to flip the top k pancakes in the stack
    public static void flip(int[] arr, int k) {
        int left = 0;
        int right = k - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    // Function to find the index of the largest pancake in the stack
    public static int findMaxIndex(int[] arr, int n) {
        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    // Recursive function to sort the pancakes
    public static void pancakeSort(int[] arr, int n) {
        // Base case: if the size is 1, no need to sort
        if (n == 1) {
            return;
        }

        // Find the index of the largest pancake in the stack
        int maxIndex = findMaxIndex(arr, n);

        // If the largest pancake is not at the top, flip it to the top
        if (maxIndex != 0) {
            flip(arr, maxIndex + 1);
        }

        // Flip the entire stack to move the largest pancake to its final position
        flip(arr, n);

        // Recur for the remaining stack of size n-1
        pancakeSort(arr, n - 1);
    }
}
