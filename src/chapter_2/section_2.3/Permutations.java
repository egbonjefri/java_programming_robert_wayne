/**
 * Implements Heap's Algorithm for generating all possible permutations of a given array.
 * Heap's Algorithm can be applied both recursively and iteratively to generate permutations
 * by systematically swapping elements. It ensures each possible permutation is created by:
 * 
 * Recursive Approach:
 * 1. If the size is 1, output the permutation.
 * 2. For each element in the array:
 *    a. Recursively solve for n-1 elements.
 *    b. Swap elements to bring a new element as the nth element for even and odd positions,
 *       ensuring all permutations are covered.
 *    c. For even n, swap the first and last element. For odd n, swap the ith and last element.
 * 
 * Iterative Approach:
 * 1. Use a stack (or an array) to simulate recursion depth, storing each level's state.
 * 2. Iterate over the array elements, swapping them according to the algorithm's rules to
 *    generate permutations. This method involves managing the index positions manually
 *    to simulate the recursive call stack, effectively iterating through each permutation.
 * 
 * Both methods efficiently generate all permutations in-place, with O(n!) time complexity,
 * due to the factorial number of permutations. The algorithm is notable for minimizing the number
 * of swaps needed to produce all possible permutations of the array, making it highly efficient
 * for permutation generation tasks.
 */




public class Permutations {
    // Method to print the array
    private static void printArray(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    // Utility function to swap elements in the array
    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Recursive function to generate permutations
    private static void generatePermutations(char[] arr, int startIndex, int k) {
        if (startIndex == k) {
            for (int i = 0; i < k; i++) {
                System.out.print(arr[i]);
            }
            System.out.println();
            return;
        }

        for (int i = startIndex; i < arr.length; i++) {
            swap(arr, startIndex, i);
            generatePermutations(arr, startIndex + 1, k);
            swap(arr, startIndex, i); // backtrack
        }
    }
    // Iterative method to generate permutations
    public static void generatePermutations(char[] arr) {
  
        int[] indices = new int[arr.length]; // To track the index positions for swapping
        int i = 0;
        printArray(arr); // Print the initial state (first permutation)
        while (i < arr.length) {
            if (indices[i] < i) {
                // Swap depending on the parity of the index i
                swap(arr, i % 2 == 0 ? 0 : indices[i], i);
                printArray(arr); // Print the current permutation
                indices[i]++; // Move to the next position
                i = 0; // Reset i to generate the next permutation sequence
            } else {
                // Reset and move to the next index if all permutations at this level are done
                indices[i] = 0;
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]); // Total number of elements
        int k = Integer.parseInt(args[1]); // Number of elements to permute

        // Generate an array of characters from 0 to n-1
        char[] elements = new char[n];
        for (int i = 0; i < n; i++) {
            elements[i] = (char) ('a' + i); 
        }

        generatePermutations(elements, 0, k);
    }
}
