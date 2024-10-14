import generators.TransitionMatrixGenerator;
import lib.StdOut;
import lib.StdDraw;
import lib.Stopwatch;
import java.awt.Color;

public class Markov {
    private static Color color[] = {StdDraw.BLUE, StdDraw.RED, StdDraw.GREEN, StdDraw.GRAY};
    private static void runMarkov(double[][] p, int n, double trials) {
        double[] rank = new double[n];
        rank[0] = 1.0;
        for (int t = 0; t < trials; t++) {
            double[] newRank = new double[n];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    newRank[j] += rank[k] * p[k][j];
                }
            }
            rank = newRank;
        }
    }
    private static void plotResults(int[] nValues, int[] trialsValues, double[][] times) {
        // Set up the canvas
        StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(0, nValues[nValues.length - 1] * 1.1);
        StdDraw.setYscale(0, trialsValues[trialsValues.length - 1] * 1.1);
        StdDraw.setPenRadius(0.005);

        // Plot the results
        for (int i = 0; i < nValues.length; i++) {
            StdDraw.setPenColor(color[i]);
            for (int j = 0; j < trialsValues.length; j++) {
                StdDraw.point(nValues[i], times[i][j]);
                if (i > 0) {
                    StdDraw.line(nValues[i - 1], times[i - 1][j], nValues[i], times[i][j]);
                }
                if (j > 0) {
                    StdDraw.line(nValues[i], times[i][j - 1], nValues[i], times[i][j]);
                }
            }
        }

        // Draw labels and axes
        StdDraw.text(nValues[nValues.length - 1] / 2, times[0][trialsValues.length - 1] * 1.05, "Markov Running Time");
        StdDraw.text(nValues[nValues.length - 1] / 2, -times[0][trialsValues.length - 1] * 0.05, "Input Size (n)");
        StdDraw.text(-nValues[nValues.length - 1] * 0.05, times[0][trialsValues.length - 1] / 2, "Time (ms)", 90);

        // Draw x-axis and y-axis
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(0, 0, nValues[nValues.length - 1] * 1.1, 0);
        StdDraw.line(0, 0, 0, times[0][trialsValues.length - 1] * 1.1);
    }

    public static void main(String[] args) {
        int[] nValues = {100, 200, 400, 800};  
        int[] trialsValues = {1000, 2000, 4000, 8000};
        double[][] times = new double[nValues.length][trialsValues.length];

        for (int i = 0; i < nValues.length; i++) {
            for (int j = 0; j < trialsValues.length; j++) {
                int n = nValues[i];
                int trials = trialsValues[j];
                double[][] p = TransitionMatrixGenerator.generateTransitionMatrix(n);
                Stopwatch timer = new Stopwatch();
                runMarkov(p, n, trials);
                times[i][j] = timer.elapsedTime();
                StdOut.printf("n = %d, trials = %d, Time: %d ms\n", n, trials, timer.elapsedTime());
            }
        }

        plotResults(nValues, trialsValues, times);
    }

}
