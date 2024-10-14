/*
 * Given an n-by-n subarray of black (1) and white (0)
pixels, design a linear-time algorithm that ﬁnds the largest square subarray that
contains no white pixels. In the following example, the largest such subarray is the
3-by-3 subarray highlighted in blue.
1 0 1 1 1 0 0 0
0 0 0 1 0 1 0 0
0 0 1 1 1 0 0 0
0 0 1 1 1 0 1 0
0 0 1 1 1 1 1 1
0 1 0 1 1 1 1 0
0 1 0 1 1 0 1 0
0 0 0 1 1 1 1 0
Implement your algorithm and conﬁrm that the order of growth of its running
time is linear in the number of pixels. Extra credit : Design an algorithm to ﬁnd the
largest rectangular black subarray.
 */

 import lib.StdRandom;
 import lib.StdDraw;
 import lib.Stopwatch;



public class PatternMatching {
    public static int largestSquareSubarray(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        int maxSize = 0;

        // Fill the dp array
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    }
                    maxSize = Math.max(maxSize, dp[i][j]);
                }
            }
        }
        return maxSize;
    }

    public static int[][] generateArray(int size){
        int[][] arr = new int[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(StdRandom.bernoulli(0.6)){
                    arr[i][j] = 1;
                }
            }
        }
        return arr;
    }
    public static void visualizeResults(int[] testSizes, double[] times) {
        double[] scaledTests = StdRandom.scaleArrayToUnitInterval(testSizes);
        double[] scaledTimes = StdRandom.scaleArrayToUnitInterval(times);
        StdDraw.setXscale(-0.1, 1.1);
        StdDraw.setYscale(-0.1, 1.1);
        // Plot the results
        for (int i = 0; i < testSizes.length; i++) {
            double x = scaledTests[i];
            double y = scaledTimes[i];
            StdDraw.filledCircle(x, y, 0.02); 
        }
    }
    public static void main(String[] args){
        int[] sizes = {100, 1000, 2000, 4000, 8000, 10000};
        double[] times = new double[sizes.length];

        for(int i = 0; i < sizes.length; i++){
            int[][] arr = generateArray(sizes[i]);
            Stopwatch timer = new Stopwatch();
            largestSquareSubarray(arr);
            times[i] = timer.elapsedTime();
        }

        visualizeResults(sizes, times);
    }
}
