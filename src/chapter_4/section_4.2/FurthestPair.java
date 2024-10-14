/*
 * Given an array of n real numbers, design a linear-time algo-
rithm to ﬁnd a pair of numbers that are furthest apart in value.
 */

import lib.StdOut;
public class FurthestPair {

    public static double[] findFurthestPair(double[] arr) {
        if (arr == null || arr.length < 2) {
            return null;  // Not enough elements to form a pair
        }

        double minVal = arr[0];
        double maxVal = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < minVal) {
                minVal = arr[i];
            }
            if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
        }

        return new double[]{minVal, maxVal};
    }

    public static void main(String[] args) {
        double[] arr = {4.2, 1.9, 5.6, 3.8, 2.1};
        double[] furthestPair = findFurthestPair(arr);
        if (furthestPair != null) {
            StdOut.println("Furthest pair: (" + furthestPair[0] + ", " + furthestPair[1] + ")");
        } else {
            StdOut.println("Not enough elements to form a pair");
        }
    }
}
