import lib.StdDraw;
import lib.Gaussian;
/*
 * Write a Gaussian and StdStats client that explores the effects of changing
the mean and standard deviation for the Gaussian probability density function.
Create one plot with the Gaussian distributions having a ﬁxed mean and various
standard deviations and another with Gaussian distributions having a ﬁxed stan-
dard deviation and various means.
 */
public class GaussianClient {

    
    private static void plotFixedMean(double mean, double[] stdDevs, int numPoints) {
        StdDraw.setCanvasSize(800, 400);
        StdDraw.setXscale(-5, 5);
        StdDraw.setYscale(0, 1);

        for (double stdDev : stdDevs) {
            double[] x = new double[numPoints];
            double[] y = new double[numPoints];

            for (int i = 0; i < numPoints; i++) {
                x[i] = -5 + 10.0 * i / (numPoints - 1);
                y[i] = Gaussian.pdf(x[i], mean, stdDev);
            }

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.line(x[0], y[0], x[numPoints - 1], y[numPoints - 1]);

            StdDraw.setPenColor(StdDraw.getPenColor().darker());
            for (int i = 1; i < numPoints; i++) {
                StdDraw.line(x[i - 1], y[i - 1], x[i], y[i]);
            }
        }
    }

    // Method to plot Gaussian distributions with fixed standard deviation and various means
    private static void plotFixedStdDev(double stdDev, double[] means, int numPoints) {
        StdDraw.setCanvasSize(800, 400);
        StdDraw.setXscale(-5, 5);
        StdDraw.setYscale(0, 1);

        for (double mean : means) {
            double[] x = new double[numPoints];
            double[] y = new double[numPoints];

            for (int i = 0; i < numPoints; i++) {
                x[i] = -5 + 10.0 * i / (numPoints - 1);
                y[i] = Gaussian.pdf(x[i], mean, stdDev);
            }

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.line(x[0], y[0], x[numPoints - 1], y[numPoints - 1]);

            StdDraw.setPenColor(StdDraw.getPenColor().darker());
            for (int i = 1; i < numPoints; i++) {
                StdDraw.line(x[i - 1], y[i - 1], x[i], y[i]);
            }
        }
    }

    public static void main(String[] args) {
        double fixedMean = 0.0;
        double[] stdDevs = {0.5, 1.0, 1.5, 2.0};
        int numPoints = 1000;

        // Plot Gaussians with fixed mean and various standard deviations
        StdDraw.clear();
        plotFixedMean(fixedMean, stdDevs, numPoints);

        // Pause to view the plot
        StdDraw.pause(2000);

        double fixedStdDev = 1.0;
        double[] means = {-2.0, 0.0, 2.0, 4.0};

        // Plot Gaussians with fixed standard deviation and various means
        StdDraw.clear();
        plotFixedStdDev(fixedStdDev, means, numPoints);
    }
}
