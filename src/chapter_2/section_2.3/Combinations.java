public class Combinations {

    // Utility function to print an array of combinations
    private static void printArray(char[] arr, int k) {
        for (int i = 0; i < k; i++) {
            System.out.print(arr[i]);
            if (i < k - 1) System.out.print("");
        }
        System.out.println();
    }

    // Recursive function to generate combinations
    private static void generateCombinations(int n, int k, int startIndex, int currentSize, char[] currentCombination) {
        // Base case: if the combination size is k, print it
        if (currentSize == k) {
            printArray(currentCombination, k);
            return;
        }

        // Start from the next element till n
        for (int i = startIndex; i < n; i++) {
            currentCombination[currentSize] = (char) ('a' + i); 
            generateCombinations(n, k, i + 1, currentSize + 1, currentCombination);
            // No need to explicitly backtrack because we overwrite the current position in each iteration
        }
    }

    public static void main(String[] args) {
        int n = 5; // Total number of elements
        int k = 3; // Size of each combination

        // Generate an array of characters from 0 to n-1
        char[] elements = new char[n];
        for (int i = 0; i < n; i++) {
            elements[i] = (char) ('a' + i); 
        }
        // Generate combinations
        generateCombinations(n, k, 0, 0, elements);
        
    }
}
