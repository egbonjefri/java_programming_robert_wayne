/*
 * Design a linear-time algorithm that ﬁnds a contiguous
subarray of length at most m in an array of n long integers that has the highest sum
among all such subarrays. Implement your algorithm, and conﬁrm that the order
of growth of its running time is linear.
 */

 import lib.StdRandom;
 import lib.StdDraw;
 import lib.Stopwatch;


public class MaximumSubarraySum {
    public static int findMaxSubarraySum(int[] nums, int m) {
        int n = nums.length;
        if (n == 0 || m <= 0) {
            return 0;
        }

        int maxSum = Integer.MIN_VALUE;
        int windowSum = 0;
        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < n; windowEnd++) {
            windowSum += nums[windowEnd];

            // If window size exceeds m, shrink the window from the left
            if (windowEnd - windowStart + 1 > m) {
                windowSum -= nums[windowStart];
                windowStart++;
            }

            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }
    public static void confirmLinearTime() {
        int[] sizes = {100000, 200000, 400000, 800000, 1600000, 3200000};
        double[] times = new double[sizes.length];
        int m = 100;  //fixed window size

        for (int size = 0; size < sizes.length; size++) {
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = StdRandom.uniformInt(201) - 100;  // Random integers between -100 and 100
            }

            Stopwatch timer = new Stopwatch();
            findMaxSubarraySum(arr, m);
            times[size] = timer.elapsedTime();
        }

        visualizeResults(sizes, times);
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
    public static void main(String[] args) {

        confirmLinearTime();
    }
}
