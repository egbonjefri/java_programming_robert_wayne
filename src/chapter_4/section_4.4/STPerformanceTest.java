/*
 * Run experiments to validate the claims in the text that the put operations
and get requests for Lookup and Index are logarithmic in the size of the table when
using ST. Develop test clients that generate random keys and also run tests for vari-
ous data sets, either from the booksite or of your own choosing.
 */
import lib.StdDraw;
import lib.Stopwatch;
import lib.ST;
import lib.StdOut;
import java.awt.Color;

public class STPerformanceTest {
    private static final int MAX_N = 1000000;
    private static final int STEP = 10000;
    private static final int TRIALS = 1000;

    public static void main(String[] args) {
        double[] sizes = new double[MAX_N / STEP];
        double[] putTimes = new double[MAX_N / STEP];
        double[] getTimes = new double[MAX_N / STEP];
        
        int i = 0;
        for (int n = STEP; n <= MAX_N; n += STEP) {
            double putTime = timePutOperations(n);
            double getTime = timeGetOperations(n);

            sizes[i] = n;
            putTimes[i] = (putTime);
            getTimes[i] = (getTime);

            i++;

            StdOut.printf("N: %d, Put Time: %.6f, Get Time: %.6f%n", n, putTime, getTime);
        }

        plotResults(sizes, putTimes, getTimes);
    }

    private static double timePutOperations(int n) {
        Stopwatch timer = new Stopwatch();
        for (int t = 0; t < TRIALS; t++) {
            ST<Integer, Integer> map = new ST<>();
            for (int i = 0; i < n; i++) {
                map.put(i, i);
            }
        }
        return timer.elapsedTime() / TRIALS;
    }

    private static double timeGetOperations(int n) {
        ST<Integer, Integer> map = new ST<>();
        for (int i = 0; i < n; i++) {
            map.put(i, i);
        }

        Stopwatch timer = new Stopwatch();
        for (int t = 0; t < TRIALS; t++) {
            for (int i = 0; i < n; i++) {
                map.get(i);
            }
        }
        return timer.elapsedTime() / TRIALS;
    }

    private static void plotResults(double[] sizes, double[] putTimes, double[] getTimes) {
        StdDraw.setCanvasSize(800, 400);
        StdDraw.setXscale(0, MAX_N);
        double maxY = getMaxValue(putTimes, getTimes);
        StdDraw.setYscale(0, maxY);
    
        drawLinePlot(sizes, putTimes, StdDraw.RED, "Put Operations");
        drawLinePlot(sizes, getTimes, StdDraw.BLUE, "Get Operations");
    
        drawAxesAndLabels(maxY);
    }
    
    private static void drawLinePlot(double[] x, double[] y, Color color, String label) {
        StdDraw.setPenColor(color);
        for (int i = 1; i < x.length; i++) {
            StdDraw.line(x[i-1], y[i-1], x[i], y[i]);
        }
        StdDraw.text(MAX_N * 0.1, getMaxValue(y) * 0.95, label);
    }
    
    private static void drawAxesAndLabels(double maxY) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(0, 0, MAX_N, 0);
        StdDraw.line(0, 0, 0, maxY);
    
        StdDraw.text(MAX_N / 2, -maxY * 0.05, "Input Size (N)");
        StdDraw.text(-MAX_N * 0.05, maxY / 2, "Time (seconds)", 90);
        StdDraw.text(MAX_N / 2, maxY * 1.05, "TreeMap Performance: Put and Get Operations");
    }
    

    private static double getMaxValue(double[]... arrays) {
        double maxValue = Double.MIN_VALUE;
    
        for (double[] arr : arrays) {
            for (double num : arr) {
                if (num > maxValue) {
                    maxValue = num;
                }
            }
        }
        return maxValue;
    }
}