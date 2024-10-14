import lib.StdRandom;
import lib.StdDraw;


public class Brownian {

    // Recursive method to draw a Brownian curve from (x0, y0) to (x1, y1) with random perturbations in both x and y directions
    public static void curve(double x0, double y0, double x1, double y1, double var, double s) {
        if (x1 - x0 < 0.01 && y1 - y0 < 0.01) {
            StdDraw.line(x0, y0, x1, y1);
            return;
        }

        double xm = (x0 + x1) / 2 + StdRandom.gaussian(0, Math.sqrt(var));
        double ym = (y0 + y1) / 2 + StdRandom.gaussian(0, Math.sqrt(var));

        curve(x0, y0, xm, ym, var / s, s);
        curve(xm, ym, x1, y1, var / s, s);
    }


    public static void main(String[] args) {
        // Parse the Hurst exponent from command line arguments
        double hurst = Double.parseDouble(args[0]);

        // Calculate the scale factor 's' from the Hurst exponent
        double s = Math.pow(2, 2 * hurst);

        // Initialize drawing canvas (optional, depending on how StdDraw is set up)
        StdDraw.setCanvasSize(512, 512);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);

        // Draw the Brownian bridge from (0, 0.5) to (1.0, 0.5) with initial variance
        curve(0, 0.5, 1.0, 0.5, 0.01, s);
    }
}
