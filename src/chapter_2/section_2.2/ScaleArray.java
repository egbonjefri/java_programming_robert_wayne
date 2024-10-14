import lib.StdStats;
import lib.StdArrayIO;
import lib.StdOut;
/*
 * Write a static method that takes double values ymin and ymax (with ymin
strictly less than ymax), and a double array a[] as arguments and uses the StdStats
library to linearly scale the values in a[] so that they are all between ymin and ymax.
 */
public class ScaleArray {
    /**
     * Scales the values in array a[] so that they are all between ymin and ymax.
     * Linear transformation formula:
     * scaled_value = ymin + (original_value - min) * (ymax - ymin) / (max - min)
     * 
     * @param ymin minimum value of the scaled range
     * @param ymax maximum value of the scaled range
     * @param a    array to be scaled
     */
    public static void scaleToRange(double ymin, double ymax, double[] a) {
        // Ensure ymin is strictly less than ymax
        if (ymin >= ymax) {
            throw new IllegalArgumentException("ymin must be strictly less than ymax");
        }

        // Find the minimum and maximum values in the array a[]
        double min = StdStats.min(a);
        double max = StdStats.max(a);

        // Check if all values in a[] are the same
        if (min == max) {
            throw new IllegalArgumentException("Array elements must not all be the same value");
        }

        // Scale the values in a[] using the linear transformation formula
        //
        for (int i = 0; i < a.length; i++) {
            a[i] = ymin + (a[i] - min) * (ymax - ymin) / (max - min);
        }
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            StdOut.println("Usage: java ScaleArray ymin ymax value1 value2 ... valueN");
            return;
        }

        try {
            double ymin = Double.parseDouble(args[0]);
            double ymax = Double.parseDouble(args[1]);

            double[] array = new double[args.length - 2];
            for (int i = 2; i < args.length; i++) {
                array[i - 2] = Double.parseDouble(args[i]);
            }

        StdOut.println("Original array:");
        StdArrayIO.print(array);
        scaleToRange(ymin, ymax, array);

        StdOut.println("\nScaled array:");
        StdArrayIO.print(array);
    } catch (NumberFormatException e) {
        StdOut.println("Invalid input. Please ensure ymin, ymax, and all array values are valid doubles.");
    } catch (IllegalArgumentException e) {
        StdOut.println(e.getMessage());
    }
    }
}
