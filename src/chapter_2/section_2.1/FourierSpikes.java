/**
 * This program plots the function (cos(t) + cos(2t) + cos(3t) + ... + cos(nt)) / n
 * for 500 equally spaced samples of t from -10 to 10 (in radians).
 * 
 * The function converges to a spike (0 everywhere except at a single value) due to:
 * 
 * 1. Interference of Cosine Waves: Each term in the series cos(kt) is a cosine wave with a different
 *    frequency. These waves interfere with each other, and at most points, their positive and negative
 *    parts cancel out, especially when t is not near zero.
 * 
 * 2. Constructive Interference at t = 0: At t = 0, all cosine terms are equal to 1, leading to their
 *    constructive interference and a maximum sum value before normalization.
 * 
 * 3. Gibbs Phenomenon: Similar to the overshooting observed in Fourier series around discontinuities,
 *    the sum of waves tends to create ripples or spikes near t = 0. As n increases, the sum gets
 *    sharper and more localized around this point.
 * 
 * 4. Normalization by n: The sum of cosines is divided by n. While the sum at t = 0 increases linearly
 *    with n, after division, the peak value remains constant at 1. For other t values, the cosines
 *    don't add constructively, so their normalized sum tends to 0.
 * 
 * As a result, for large n, the function converges to 0 for most t values except near t = 0, where it
 * spikes to 1, creating a sharp, spike-like plot.
 */

import lib.StdDraw;
public class FourierSpikes {

    public static void main(String[] args) {
        // Check for command-line argument
        if (args.length < 1) {
            System.out.println("Usage: java FourierSpikes <n>");
            System.exit(1);
        }

        // Parse the command-line argument
        int n = Integer.parseInt(args[0]);

        // Set the scale for the plot
        StdDraw.setXscale(-10, 10);
        StdDraw.setYscale(-2, 2);

        // Number of samples
        int samples = 500;
        double[] x = new double[samples];
        double[] y = new double[samples];

        // Calculate the function values
        for (int i = 0; i < samples; i++) {
            x[i] = -10 + 20.0 * i / samples;
            y[i] = calculateFunctionValue(x[i], n);
        }
        // Draw the axes
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.line(-10, 0, 10, 0); // x-axis
        StdDraw.line(0, -2, 0, 2); // y-axis

        // Draw the tick marks and labels for x-axis
        for (double i = -10; i <= 10; i += 2) {
            StdDraw.line(i, -0.1, i, 0.1); // tick mark
            StdDraw.text(i, -0.2, String.format("%.1f", i)); // label
        }

        // Draw the tick marks and labels for y-axis
        for (double i = -2; i <= 2; i += 0.5) {
            StdDraw.line(-0.2, i, 0.2, i); // tick mark
            StdDraw.text(-0.5, i, String.format("%.1f", i)); // label
        }

        // Label the axes
        StdDraw.text(0, -1.8, "t (radians)"); // x-axis label
        StdDraw.text(-9, 1.8, "f(t, n)"); // y-axis label
        StdDraw.setPenColor(StdDraw.BLACK);

        // Plot the function
        for (int i = 1; i < samples; i++) {
            StdDraw.line(x[i-1], y[i-1], x[i], y[i]);
        }
    }

    /**
     * Calculates the function value for a given t and n.
     * The formula used is:
     *
     * f(t, n) = (cos(t) + cos(2t) + cos(3t) + ... + cos(nt)) / n
     *
     * which simplifies to the average of cosines for each i from 1 to n:
     *
     * f(t, n) = 1/n * Σ from i=1 to n of cos(it)
     *
     * @param t the t value at which to evaluate the function
     * @param n the number of terms to include in the sum
     * @return the function value calculated
     */
    public static double calculateFunctionValue(double t, int n) {
        double sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += Math.cos(i * t);
        }
        return sum / n;
    }
}
