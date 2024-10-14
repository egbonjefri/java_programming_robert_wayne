import lib.StdIn;
import lib.StdOut;

/*
 * This program reads the graph structure from StdIn, 
 * simulates the random surfer model, and checks for 
 * convergence at each iteration. It tracks how many iterations 
 * are required to achieve accuracy to 4 and 5 decimal places. 
 */


public class RandomSurferAccuracy {
    public static void main(String[] args) {
        int n = StdIn.readInt(); // Read the number of pages
        double[][] p = new double[n][n]; // Transition matrix
        double[] rank = new double[n]; // Current page ranks
        double[] newRank = new double[n]; // Page ranks for the next iteration
        double[] prevRank = new double[n]; // Page ranks from the previous iteration

        // Initialize ranks
        for (int i = 0; i < n; i++) {
            rank[i] = 1.0 / n;
            prevRank[i] = 1.0 / n;
        }

        // Read the transition matrix from StdIn
        while (!StdIn.isEmpty()) {
            int i = StdIn.readInt();
            int j = StdIn.readInt();
            p[i][j]++; // Increment the count of links
        }

        // Normalize the transition matrix
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += p[i][j];
            }
            if (sum != 0) {
                for (int j = 0; j < n; j++) {
                    p[i][j] /= sum;
                }
            }
        }

        int iterations = 0;
        boolean converged4dp = false;
        boolean converged5dp = false;

        // Run simulations
        while (!converged4dp || !converged5dp) {
            iterations++;
            System.arraycopy(rank, 0, prevRank, 0, n); // Copy current ranks to previous ranks

            // Simulate one step
            for (int i = 0; i < n; i++) {
                newRank[i] = 0;
                for (int j = 0; j < n; j++) {
                    newRank[i] += rank[j] * p[j][i];
                }
            }

            System.arraycopy(newRank, 0, rank, 0, n); // Update ranks

            // Check for convergence to 4 and 5 decimal places
            converged4dp = true;
            converged5dp = true;
            for (int i = 0; i < n; i++) {
                double diff = Math.abs(rank[i] - prevRank[i]);
                if (diff >= 0.0001) converged4dp = false;
                if (diff >= 0.00001) converged5dp = false;
            }
        }

        // Print results
        StdOut.println("Iterations required for 4 decimal places: " + (converged4dp ? iterations : "Not converged"));
        StdOut.println("Iterations required for 5 decimal places: " + (converged5dp ? iterations : "Not converged"));
    }
}
