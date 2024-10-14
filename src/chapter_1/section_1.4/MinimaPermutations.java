import lib.StdOut;
import lib.StdRandom;
import lib.StdArrayIO;
/*
 * Write a program that takes an integer command-
line argument n, generates a random permutation, prints the permutation, and
prints the number of left-to-right minima in the permutation (the number of
times an element is the smallest seen so far). Then write a program that takes two
integer command-line arguments m and n, generates m random permutations of
length n, and prints the average number of left-to-right minima in the permuta-
tions generated. Extra credit : Formulate a hypothesis about the number of left-to-
right minima in a permutation of length n, as a function of n.
 */

public class MinimaPermutations {
    public static void main(String[] args) {
        if (args.length != 2) {
            StdOut.println("Usage: java MinimaPermutations <n>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        int[] permutation = new int[n];
        int[][] output = new int[n][n];

        // Initialize the permutation array
        for (int i = 0; i < n; i++) {
            permutation[i] = i + 1;
        }

        // Shuffle the array to create m random permutations
        for(int j = 0; j < m; j++){
        for (int i = 0; i < n; i++) {
            int r = i + StdRandom.uniformInt(n - i);
            int temp = permutation[i];
            permutation[i] = permutation[r];
            permutation[r] = temp;
            output[j][i] = permutation[i];
        }
        // Count and print the number of left-to-right minima
        int countMinima = 0;
        int currentMin = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (permutation[i] < currentMin) {
                currentMin = permutation[i];
                countMinima++;
            }
        }

        StdOut.println("Number of left-to-right minima: " + countMinima);
        }
        //Print the 2D output array
        StdArrayIO.print(output);
    }
}
