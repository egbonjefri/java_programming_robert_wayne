/*
 * Write a program that ﬁnds a contiguous subarray of
length at most m in an array of n long integers that has the highest average val-
ue among all such subarrays, by trying all subarrays. Use the scientiﬁc method
to conﬁrm that the order of growth of the running time of your program is mn^2.

Next, write a program that solves the problem by ﬁrst computing the quantity
prefix[i] = a[0] + ... + a[i] for each i, then computing the average in the inter-
val from a[i] to a[j] with the expression (prefix[j] - prefix[i]) / (j - i + 1).
Use the scientiﬁc method to conﬁrm that this method reduces the order of growth
by a factor of n.
 */

import lib.StdDraw;
import java.util.Arrays;
import generators.RandomArrayGenerator;
import lib.Stopwatch;

public class MaximumSubarrayAverage {
    public static double findMaxAverageSubarrayBruteForce(int[] arr, int m) {
        int n = arr.length;
        double maxAvg = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < Math.min(i + m, n); j++) {
                double sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                double avg = sum / (j - i + 1);
                if (avg > maxAvg) {
                    maxAvg = avg;
                }
            }
        }

        return maxAvg;
    }
    public static double findMaxAverageSubarrayOptimized(int[] arr, int m) {
        int n = arr.length;
        double maxAvg = Double.NEGATIVE_INFINITY;
        double[] prefix = new double[n + 1];

        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + arr[i - 1];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < Math.min(i + m, n); j++) {
                double sum = prefix[j + 1] - prefix[i];
                double avg = sum / (j - i + 1);
                if (avg > maxAvg) {
                    maxAvg = avg;
                }
            }
        }

        return maxAvg;
    }

    public static void draw(int[] sizes, double[] bruteForceTimes, double[] optimizedTimes) {
        // Setup the canvas
        StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(-1, sizes[sizes.length - 1]);
        StdDraw.setYscale(-1, Arrays.stream(bruteForceTimes).max().orElse(1));

        // Draw the axes
        StdDraw.line(0, 0, sizes[sizes.length - 1], 0);
        StdDraw.line(0, 0, 0, Arrays.stream(bruteForceTimes).max().orElse(1));

        // Draw the data points and lines
        StdDraw.setPenColor(StdDraw.RED);
        for (int i = 0; i < sizes.length - 1; i++) {
            StdDraw.line(sizes[i], bruteForceTimes[i], sizes[i + 1], bruteForceTimes[i + 1]);
        }

        StdDraw.setPenColor(StdDraw.BLUE);
        for (int i = 0; i < sizes.length - 1; i++) {
            StdDraw.line(sizes[i], optimizedTimes[i], sizes[i + 1], optimizedTimes[i + 1]);
        }

        // Add labels
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.textLeft(sizes[sizes.length - 1], bruteForceTimes[sizes.length - 1], "Brute Force");
        StdDraw.textLeft(sizes[sizes.length - 1], optimizedTimes[sizes.length - 1], "Optimized");
    }
    public static void main(String[] args) {
        int[] sizes = {100000, 200000, 400000, 800000, 1600000, 3200000};
        double[] bruteForceTimes = new double[sizes.length];
        double[] optimizedTimes = new double[sizes.length];
        int m = 100;  //fixed window size
        for (int size = 0; size < sizes.length; size++) {
            int[] arr = RandomArrayGenerator.generateIntArray(sizes[size]);
            Stopwatch timer = new Stopwatch();
            findMaxAverageSubarrayBruteForce(arr, m);
            double duration1 = timer.elapsedTime();
            bruteForceTimes[size] = duration1;

            Stopwatch timer_2 = new Stopwatch();
            findMaxAverageSubarrayOptimized(arr, m);
            double duration2 = timer_2.elapsedTime();
            optimizedTimes[size] = duration2;
        }

        draw(sizes, bruteForceTimes, optimizedTimes);
    }
}


