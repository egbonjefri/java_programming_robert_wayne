import lib.StdDraw;
import lib.BinarySearchST;
import lib.SequentialSearchST;
import lib.ST;
import lib.Stopwatch;
import lib.StdRandom;
import lib.LinearRegression;


public class STBenchmark {
    private static final int MAX_TRIALS = 500;
    public static void main(String[] args) {
        BinarySearchST<String, Integer> bst = new BinarySearchST<>();
        SequentialSearchST<String, Integer> sst = new SequentialSearchST<>();
        ST<String, Integer> treeMap = new ST<>();

        double[] bstTimes = new double[MAX_TRIALS];
        double[] treeMapTimes = new double[MAX_TRIALS];
        double[] sstTimes = new double[MAX_TRIALS];

         for (int n = 1; n <= MAX_TRIALS; n++) {
            String[] strings = generateRandomStrings(n);

            Stopwatch timer1 = new Stopwatch();
            for (String s : strings) {
                if (!bst.contains(s)){
                    bst.put(s,1);
            }
            else{
                Integer currentValue = bst.get(s);
                int newValue = currentValue + 1;
                bst.put(s, newValue);
            }
        }
            bstTimes[n - 1] = timer1.elapsedTime();

            
            Stopwatch timer2 = new Stopwatch();
            for (String s : strings) {
                if (!treeMap.contains(s)){
                    treeMap.put(s,1);
            }
            else{
                Integer currentValue = treeMap.get(s);
                int newValue = currentValue + 1;
                treeMap.put(s, newValue);
            }
            }
            treeMapTimes[n - 1] = timer2.elapsedTime();

            Stopwatch timer3 = new Stopwatch();
            for (String s : strings) {
                if (!sst.contains(s)){
                    sst.put(s,1);
            }
            else{
                Integer currentValue = sst.get(s);
                int newValue = currentValue + 1;
                sst.put(s, newValue);
            }
            }
            sstTimes[n - 1] = timer3.elapsedTime();
        }
        
        plotResults(bstTimes, treeMapTimes, sstTimes);
    }    
    private static void plotResults(double[] bstTimes, double[] treeMapTimes, double[] sstTimes) {
        StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(0, MAX_TRIALS);
        StdDraw.setYscale(0, getMaxValue(bstTimes, treeMapTimes, sstTimes));
        StdDraw.setPenRadius(0.035);

        // Plot BST times
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        for (int i = 0; i < bstTimes.length; i++) {
            StdDraw.point(i, bstTimes[i]);
        }

        // Plot TreeMap times
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        for (int i = 0; i < treeMapTimes.length; i++) {
            StdDraw.point(i, treeMapTimes[i]);
        }
        // Plot Sequential LinkedList times
        StdDraw.setPenColor(StdDraw.GREEN);
        for (int i = 0; i < sstTimes.length; i++) {
            StdDraw.point(i, sstTimes[i]);
        }

        // Perform linear regression
        double[] x = new double[MAX_TRIALS];
        for (int i = 0; i < MAX_TRIALS; i++) {
            x[i] = i;
        }

        LinearRegression bstRegression = new LinearRegression(x, bstTimes);
        LinearRegression treeMapRegression = new LinearRegression(x, treeMapTimes);
        LinearRegression sstRegression = new LinearRegression(x, sstTimes);

        // Plot regression lines
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(0, bstRegression.intercept(), MAX_TRIALS, bstRegression.slope() * MAX_TRIALS + bstRegression.intercept());

        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        StdDraw.line(0, treeMapRegression.intercept(), MAX_TRIALS, treeMapRegression.slope() * MAX_TRIALS + treeMapRegression.intercept());

        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.line(0, sstRegression.intercept(), MAX_TRIALS, sstRegression.slope() * MAX_TRIALS + sstRegression.intercept());
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
    private static String[] generateRandomStrings(int n) {
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = generateRandomString(StdRandom.uniformInt(100));
        }
        return strings;
    }
    private static String[] generateFixedStrings(int n) {
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = "abcde";
        }
        return strings;
    }

    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + StdRandom.uniformInt(26)));
        }
        return sb.toString();
    }
}
