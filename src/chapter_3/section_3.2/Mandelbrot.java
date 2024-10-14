/******************************************************************************
 *  Compilation:  javac Mandelbrot.java
 *  Execution:    java Mandelbrot xc yc size
 *  Dependencies: StdDraw.java
 *
 *  Plots the size-by-size region of the Mandelbrot set, centered on (xc, yc)
 *
 *  % java Mandelbrot -0.5 0 2
 *
 ******************************************************************************/

import lib.StdOut;
import lib.Stopwatch;
import lib.Complex;

 public class Mandelbrot {
 
     // return number of iterations to check if c = a + ib is in Mandelbrot set
     public static int mand(Complex z0, int max) {
         Complex z = z0;
         for (int t = 0; t < max; t++) {
             if (z.abs() > 2.0) return t;
             z = z.times(z).plus(z0);
         }
         return max;
     }
     // Return true if the point (x0, y0) is in the Mandelbrot set
     public static boolean isInMandelbrotSet(double x0, double y0, int maxIterations) {
        double x = 0.0, y = 0.0;
        double xSquared = 0.0, ySquared = 0.0;  // To avoid repeated calculation
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            if (xSquared + ySquared > 4.0) {
                return false;  // The sequence diverges
            }
            double xTemp = xSquared - ySquared + x0;
            y = 2 * x * y + y0;
            x = xTemp;

            xSquared = x * x;
            ySquared = y * y;
        }
        return true;  
    }
    public static boolean isInMandelbrotSet(Complex z0, int maxIterations) {
        Complex z = new Complex(0, 0);  // Start at the origin
        for (int t = 0; t < maxIterations; t++) {
            if (z.abs() > 2.0) {
                return false;  
            }
            z = z.times(z).plus(z0);
        }
        return true;  
    }
    /*
     * Write a Stopwatch client that compares the cost of using
Complex to the cost of writing code that directly manipulates two double values,
for the task of doing the calculations in Mandelbrot. Speciﬁcally, create a version
of Mandelbrot that just does the calculations (remove the code that refers to Pic-
ture), then create a version of that program that does not use Complex, and then
compute the ratio of the running times.
     */
     public static void main(String[] args)  {
         double xc   = Double.parseDouble(args[0]);
         double yc   = Double.parseDouble(args[1]);
         int max = 255; // Maximum number of iterations

         Complex z0 = new Complex(xc, yc);
         Stopwatch timer1 = new Stopwatch();
         boolean complexSet = isInMandelbrotSet(z0, max);
         double time1 = timer1.elapsedTime();
         StdOut.printf("%b (%.2f seconds)\n", complexSet, time1);

         Stopwatch timer2 = new Stopwatch();
         boolean isInSet = isInMandelbrotSet(xc, yc, max);
         double time2 = timer2.elapsedTime();
         StdOut.printf("%b (%.2f seconds)\n", isInSet, time2);
     }
 }
 