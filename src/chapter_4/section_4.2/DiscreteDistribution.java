import java.util.Arrays;
import lib.StdRandom;
import lib.Stopwatch;
import lib.StdDraw;

public class DiscreteDistribution {

    // Preprocessing step: Form the cumulative sum array
    public static double[] createCumulativeSum(double[] a) {
        int n = a.length;
        double[] sum = new double[n];
        sum[0] = a[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + a[i];
        }
        return sum;
    }

    // Linear search method to sample from the distribution
    public static int sampleFromDistributionLinear(double[] sum, double r) {
        for (int i = 0; i < sum.length; i++) {
            if (r < sum[i]) {
                return i;
            }
        }
        return sum.length - 1; // Shouldn't reach here if r is [0, 1)
    }

    // Binary search method to sample from the distribution
    public static int sampleFromDistributionBinary(double[] sum, double r) {
        int index = Arrays.binarySearch(sum, r);
        if (index < 0) {
            index = -index - 1;
        }
        return index;
    }
    public static void plotTimes(double durationLinear, double durationBinary) {
        // Set up the drawing window
        StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(-1, 2);
        StdDraw.setYscale(0, Math.max(durationLinear, durationBinary) * 1.1);
    
        // Draw axes
        StdDraw.line(0, 0, 0, Math.max(durationLinear, durationBinary) * 1.1);
        StdDraw.line(0, 0, 2, 0);
    
        // Draw bars
        double barWidth = 0.5;
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledRectangle(0.5, durationLinear / 2, barWidth / 2, durationLinear / 2);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledRectangle(1.5, durationBinary / 2, barWidth / 2, durationBinary / 2);
    
        // Draw labels
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(0.5, durationLinear + 0.1, String.format("%.2f s", durationLinear));
        StdDraw.text(1.5, durationBinary + 0.1, String.format("%.2f s", durationBinary));
        StdDraw.text(0.5, -0.1, "Linear");
        StdDraw.text(1.5, -0.1, "Binary");
        StdDraw.text(-0.1, Math.max(durationLinear, durationBinary) / 2, "Time (s)", 90);
    
        // Draw title
        StdDraw.text(1, Math.max(durationLinear, durationBinary) * 1.05, "Benchmark: Linear vs Binary Search");
    
        // Show the plot
        StdDraw.show();
    }
    public static void main(String[] args) {
        double[] a = {0.1, 0.2, 0.3, 0.4};  // Example probability distribution
        int trials = 1000000; // Number of trials

        // Create cumulative sum array
        double[] sum = createCumulativeSum(a);

        // Benchmark Linear Search Method
        Stopwatch startTime = new Stopwatch();
        for (int t = 0; t < trials; t++) {
            double r = StdRandom.uniformDouble();
            sampleFromDistributionLinear(sum, r);
        }
        double durationLinear = startTime.elapsedTime();

        // Benchmark Binary Search Method
        Stopwatch startTime2 = new Stopwatch();
        for (int t = 0; t < trials; t++) {
            double r = StdRandom.uniformDouble();
            sampleFromDistributionBinary(sum, r);
        }
        double durationBinary = startTime2.elapsedTime();

        plotTimes(durationLinear, durationBinary);
    }
}
