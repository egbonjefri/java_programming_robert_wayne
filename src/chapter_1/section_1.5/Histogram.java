
import lib.StdOut;
import lib.StdIn;
import lib.StdDraw;
/*
 * Suppose that the standard input stream is a sequence of double
values. Write a program that takes an integer n and two real numbers lo and hi as
command-line arguments and uses StdDraw to plot a histogram of the count of the
numbers in the standard input stream that fall in each of the n intervals deﬁned by
dividing (lo , hi) into n equal-sized intervals.
 */

public class Histogram {

    public static void main(String[] args) {
    // Ensure there are exactly three arguments
        if (args.length != 3) {
         StdOut.println("Usage: java Histogram <n> <lo> <hi>");
        return;
            }
        // Parse command-line arguments
        int n = Integer.parseInt(args[0]);
        double lo = Double.parseDouble(args[1]);
        double hi = Double.parseDouble(args[2]);

        // Initialize count array
        int[] count = new int[n];
        double intervalSize = (hi - lo) / n;

        // Read values and increment counts
        while (!StdIn.isEmpty()) {
            double value = StdIn.readDouble();
        for (int i = 0; i < n; i++) {
            double intervalStart = lo + i * intervalSize;
            double intervalEnd = intervalStart + intervalSize;

            if (isInRange(value, intervalStart, intervalEnd)) {
                count[i]++;
            }
        }
        }
        
        StdDraw.setCanvasSize(1980, 1600);
        double border = 1; // Extra border for labels
        StdDraw.setXscale(-border, n + border);
        StdDraw.setYscale(-border, getMax(count) + border);
        StdDraw.clear(StdDraw.BLACK); 

        // Draw histogram
        for (int i = 0; i < n; i++) {
            double x = 0.3+i*0.565;
            double y = count[i] / 2.0;
            double rw = 3.5 * intervalSize;
            double rh = count[i] / 2.0;
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledRectangle(x, y, rw, rh);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(x, -border / 2, "Interval " + (i + 1));

        }
                // Add Y-axis label
                for (int i = 0; i <= getMax(count); i++) {
                    StdDraw.text(-border / 2, i, String.valueOf(i));
                }
    }

    // Utility method to get maximum value in an array
    private static int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
    public static boolean isInRange(double value, double lowerBound, double upperBound) {
        return value >= lowerBound && value <= upperBound;
    }
}
