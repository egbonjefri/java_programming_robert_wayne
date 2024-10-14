/*
Suppose that you have two six-sided dice, one with faces
labeled 1, 3, 4, 5, 6, and 8 and the other with faces labeled 1, 2, 2, 3, 3, and 4. Com-
pare the probabilities of occurrence of each of the values of the sum of the dice with
those for a standard pair of dice. Use StdRandom and StdStats.
*/

import lib.StdRandom;
import lib.StdStats;


public class SichermanDice { // Simulate rolling the custom dice
    private static int rollCustomDie1() {
        int[] faces = {1, 3, 4, 5, 6, 8};
        return faces[StdRandom.uniformInt(6)];
    }

    private static int rollCustomDie2() {
        int[] faces = {1, 2, 2, 3, 3, 4};
        return faces[StdRandom.uniformInt(6)];
    }

    // Simulate rolling a standard die
    private static int rollStandardDie() {
        return StdRandom.uniformInt(1, 7);
    }

    // Simulate the sums of rolling two custom dice
    private static void simulateCustomDice(int trials, double[] sums) {
        for (int i = 0; i < trials; i++) {
            int sum = rollCustomDie1() + rollCustomDie2();
            sums[sum] += 1.0 / trials;
        }
    }

    // Simulate the sums of rolling two standard dice
    private static void simulateStandardDice(int trials, double[] sums) {
        for (int i = 0; i < trials; i++) {
            int sum = rollStandardDie() + rollStandardDie();
            sums[sum] += 1.0 / trials;
        }
    }

    public static void main(String[] args) {
        int trials = 1000000;
        int maxSum = 13; // Maximum possible sum with custom dice

        double[] customSums = new double[maxSum + 1];
        double[] standardSums = new double[maxSum + 1];

        simulateCustomDice(trials, customSums);
        simulateStandardDice(trials, standardSums);

        StdStats.plotBars(customSums);
    }
    }
    
