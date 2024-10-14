import lib.Stack;
import lib.StackOfInts;
import lib.StdDraw; 
import lib.Stopwatch;
/* 
 * Develop a class StackOfInts that uses a linked-list representation (but
no generics) to implement a stack of integers. Write a client that compares the
performance of your implementation with Stack<Integer> to determine the per-
formance penalty from autoboxing and unboxing on your system.
 */
public class StackPerformanceComparison {
    public static void main(String[] args) {
        int maxOperations = 1000000;
        int stepSize = 10000;

        double[] operations = new double[maxOperations / stepSize];
        double[] timeStackOfInts = new double[maxOperations / stepSize];
        double[] timeStackInteger = new double[maxOperations / stepSize];

        for (int i = 0; i < maxOperations; i += stepSize) {
            operations[i / stepSize] = i;

            // Measure time for StackOfInts
            StackOfInts stackOfInts = new StackOfInts();
            Stopwatch timer1 = new Stopwatch();
            for (int j = 0; j < i; j++) {
                stackOfInts.push(j);
            }
            for (int j = 0; j < i; j++) {
                stackOfInts.pop();
            }
           
            timeStackOfInts[i / stepSize] = timer1.elapsedTime();

            // Measure time for Stack<Integer>
            Stack<Integer> stackInteger = new Stack<>();
            Stopwatch timer2 = new Stopwatch();
            for (int j = 0; j < i; j++) {
                stackInteger.push(j);
            }
            for (int j = 0; j < i; j++) {
                stackInteger.pop();
            }
           
            timeStackInteger[i / stepSize] = timer2.elapsedTime();
        }

        // Plot the results using StdDraw
        StdDraw.setXscale(0, maxOperations);
        StdDraw.setYscale(0, Math.max(maxValue(timeStackOfInts), maxValue(timeStackInteger)));
        StdDraw.setPenRadius(0.015);
        // Plot time for StackOfInts
        StdDraw.setPenColor(StdDraw.RED);
        for (int i = 0; i < operations.length; i++) {
            StdDraw.point(operations[i], timeStackOfInts[i]);
        }

        // Plot time for Stack<Integer>
        StdDraw.setPenColor(StdDraw.BLUE);
        for (int i = 0; i < operations.length; i++) {
            StdDraw.point(operations[i], timeStackInteger[i]);
        }
    }

    private static double maxValue(double[] array) {
        double max = array[0];
        for (double v : array) {
            if (v > max) max = v;
        }
        return max;
    }
}
