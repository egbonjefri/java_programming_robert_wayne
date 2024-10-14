
import lib.StdRandom;
import lib.StdOut;
import lib.Percolation;
import generators.RandomGridGenerator;

public class PercolationThresholdEstimator {
    private static final int TRIALS = 50;  // Number of trials for each probability
    private static final double EPSILON = 1e-6;  // Precision for bisection search

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);  // Grid size
        double threshold = estimateThreshold(n);
        StdOut.printf("Estimated percolation threshold for %dx%d grid: %.6f\n", n, n, threshold);
    }

    private static double estimateThreshold(int n) {
        double low = 0.0;
        double high = 1.0;

        while (high - low > EPSILON) {
            double mid = (low + high) / 2.0;
            double percolationRate = runTrials(n, mid);

            if (percolationRate > 0.5) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return (low + high) / 2.0;
    }

    private static double runTrials(int n, double probability) {
        int percolatedCount = 0;

        for (int t = 0; t < TRIALS; t++) {
            int openSites = 0;
            int totalSites = n * n;
            boolean[][] isOpen = RandomGridGenerator.generateRandomGrid(n, n, 0.5);
            while (openSites < totalSites && !Percolation.percolates(isOpen)) {
                int row, col;
                do {
                    row = StdRandom.uniformInt(n);
                    col = StdRandom.uniformInt(n);
                } while (Percolation.isOpen(row, col));

                Percolation.open(row, col);
                openSites++;

                if ((double) openSites / totalSites > probability) {
                    break;
                }
            }

            if (Percolation.percolates(isOpen)) {
                percolatedCount++;
            }
        }

        return (double) percolatedCount / TRIALS;
    }
}