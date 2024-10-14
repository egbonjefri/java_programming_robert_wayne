/*
 * Suppose that the standard input stream is a sequence
of double values. Write a program that takes an integer n and two double values
lo and hi from the command line and uses StdStats to plot a histogram of the
count of the numbers in the standard input stream that fall in each of the n inter-
vals deﬁned by dividing (lo , hi) into n equal-sized intervals. Use your program to
add code to your solution to EXERCISE 2.2.3 to plot a histogram of the distribution
of the numbers produced by each method, taking n from the command line.
 */
import lib.StdOut;
import lib.StdIn;
import lib.StdStats;


public class DynamicHistogram {
    public static boolean isInRange(double value, double lowerBound, double upperBound) {
        return value >= lowerBound && value <= upperBound;
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
        StdStats.plotBars(count,intervalSize,getMax(count));

            
}
    }