import lib.StdOut;
import lib.StdIn;

public class MarkovAccuracy {
    public static void main(String[] args) {
        int n = StdIn.readInt();
        StdIn.readInt();
        // Read transition matrix.
        double[][] p = new double[n][n];
        for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
        p[i][j] = StdIn.readDouble();
        }
    }

        // Initialize page ranks
        double[] ranks = new double[n];
        ranks[0] = 1.0;
        double[] prevRanks = new double[n];
        System.arraycopy(ranks, 0, prevRanks, 0, n);

        int iteration = 0;
        boolean converged3dp = false, converged4dp = false, converged5dp = false;

        // Use the power method to compute page ranks
        while (!converged3dp || !converged4dp || !converged5dp) {
            double[] newRanks = new double[n];

            // Compute effect of next move on page ranks
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    newRanks[j] += ranks[k] * p[k][j];
                }
            }

            // Check for convergence
            converged3dp = true;
            converged4dp = true;
            converged5dp = true;
            for (int i = 0; i < n; i++) {
                double diff = Math.abs(newRanks[i] - prevRanks[i]);
                if (diff >= 0.001) converged3dp = false;
                if (diff >= 0.0001) converged4dp = false;
                if (diff >= 0.00001) converged5dp = false;
            }

            System.arraycopy(newRanks, 0, ranks, 0, n);
            System.arraycopy(newRanks, 0, prevRanks, 0, n);
            iteration++;
        }

        // Output the results
        StdOut.println("Iterations for 3 decimal places: " + (converged3dp ? iteration : "Not converged"));
        StdOut.println("Iterations for 4 decimal places: " + (converged4dp ? iteration : "Not converged"));
        StdOut.println("Iterations for 5 decimal places: " + (converged5dp ? iteration : "Not converged"));
    }
}
