import lib.StdDraw;
import lib.StdRandom;

/*
 * B. Mandelbrot asked the famous question How long is
the coast of Britain? Modify Brownian to get a program BrownianIsland that plots
Brownian islands, whose coastlines resemble that of Great Britain. The modiﬁca-
tions are simple: ﬁrst, change curve() to add a random Gaussian to the x-coordi-
nate as well as to the y-coordinate; second, change main() to draw a curve from the
point at the center of the canvas back to itself. Experiment with various values of
the parameters to get your program to produce islands with a realistic look.
 */
public class BrownianIsland {

    // Recursive method to draw an island-like shape
    private static void curve(double x0, double y0, double x1, double y1, double var, double s, double initVar) {
        // If the line segment is short enough, or the variance is low, draw the line
        if ((Math.abs(x1 - x0) < 0.005 && Math.abs(y1 - y0) < 0.005) || var < initVar / 1000) {
            StdDraw.line(x0, y0, x1, y1);
            return;
        }
        
        // Calculate the midpoint and apply random perturbation
        double xm = (x0 + x1) / 2;
        double ym = (y0 + y1) / 2;
        xm += StdRandom.gaussian(0, Math.sqrt(var));
        ym += StdRandom.gaussian(0, Math.sqrt(var));

        // Recursively draw the other segments with reduced variance
        curve(x0, y0, xm, ym, var/s, s, initVar);
        curve(xm, ym, x1, y1, var/s, s, initVar);
    }

    // Method to start the drawing process
    public static void island(double var, double s) {
        // Starting points for the island shape
        double x0 = 0.5;
        double y0 = 0.5;

        // Draw the initial point (can be set to the center or any other point)
        StdDraw.point(x0, y0);

        // Draw four curves, each starting from the last ending point to form a closed loop
        double x1 = x0 + StdRandom.gaussian(0, Math.sqrt(var));
        double y1 = y0 + StdRandom.gaussian(0, Math.sqrt(var));
        curve(x0, y0, x1, y1, var, s, var);
        
        double x2 = x1 + StdRandom.gaussian(0, Math.sqrt(var));
        double y2 = y1 + StdRandom.gaussian(0, Math.sqrt(var));
        curve(x1, y1, x2, y2, var, s, var);

        double x3 = x2 + StdRandom.gaussian(0, Math.sqrt(var));
        double y3 = y2 + StdRandom.gaussian(0, Math.sqrt(var));
        curve(x2, y2, x3, y3, var, s, var);

        // Complete the loop by returning to the starting point
        curve(x3, y3, x0, y0, var, s, var);
    }

    public static void main(String[] args) {
        // Set up the canvas
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.setPenRadius(0.005); // Thin pen radius for finer details

        double hurst = 0.75; // Hurst exponent (try adjusting between 0.5 and 1.0)
        double var = 0.005; // Lower variance for finer perturbations
        double s = Math.pow(2, 2 * hurst); // Smoothness factor (related to the Hurst exponent)

        island(var, s);
    }
}
