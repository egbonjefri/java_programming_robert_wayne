/*
 * Given n timestamps for when a ﬁle is requested
from a web server, ﬁnd the largest interval of time in which no ﬁle is requested.
Write a program to solve this problem in linearithmic time
 */

import lib.Merge;
import lib.StdOut;


public class LargestEmptyInterval {
    // Method to find the largest interval with no file requests
    public static Long largestInterval(Long[] timestamps) {
        // If there are less than 2 timestamps, return 0 as no interval exists
        if (timestamps.length < 2) {
            return 0L;
        }

        // Sort the array of timestamps
        Merge.sort(timestamps);

        // Initialize the max interval as 0
        long maxInterval = 0;

        // Iterate through the sorted timestamps and find the maximum difference
        for (int i = 1; i < timestamps.length; i++) {
            long interval = timestamps[i] - timestamps[i - 1];
            if (interval > maxInterval) {
                maxInterval = interval;
            }
        }

        return maxInterval;
    }

    public static void main(String[] args) {
        // Example timestamps array
        Long[] timestamps = {1620000000L, 1620003600L, 1620007200L, 1620010800L};

        // Find and print the largest interval
        Long largestInterval = largestInterval(timestamps);
        StdOut.println("Largest interval with no file requests: " + largestInterval + " seconds");
    }  
}
