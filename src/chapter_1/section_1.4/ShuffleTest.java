import lib.StdOut;
import lib.StdRandom;


public class ShuffleTest {
    public static void main(String[] args) {
        if (args.length != 2) {
            StdOut.println("Usage: java ShuffleTest <M> <N>");
            return;
        }

        int M = Integer.parseInt(args[0]);
        int N = Integer.parseInt(args[1]);
        int[] array = new int[M];
        int[][] positionCount = new int[M][M];

        for (int shuffle = 0; shuffle < N; shuffle++) {
            // Initialize the array for each shuffle
            for (int i = 0; i < M; i++) {
                array[i] = i;
            }

            // Shuffle the array
            for (int i = 0; i < M; i++) {
                //Fisher-Yates Shuffle
                int r = i + StdRandom.uniformInt(M - i); // random index from i to M-1
                int temp = array[i];
                array[i] = array[r];
                array[r] = temp;
                // Update position counts
                positionCount[array[i]][r]++;
            }
        }

        // Print the M-by-M table
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                StdOut.printf("%4d", positionCount[i][j]);
            }
            StdOut.println();
        }
    }
}
// Modified Shuffle Method Analysis:
// A bad shuffle method selects a random integer between 0 and n-1 at each step, 
// rather than between i and n-1 as in the standard Fisher-Yates shuffle. 
// This modification introduces bias, making certain permutations more likely than others.
// In a uniform shuffle (Fisher-Yates), each element has an equal chance of 
// occupying any position, ensuring all n! permutations are equally likely. 
// However, by choosing from the full range at each step, early elements are more 
// likely to be moved multiple times, disrupting this uniformity. 
// Consequently, the array's permutations are no longer uniformly distributed with this method.
