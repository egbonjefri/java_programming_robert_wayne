/*
 * Develop a StdRandom client to study the gambler’s ruin
problem (see PROGRAM 1.3.8 and EXERCISE 1.3.24–25). Note : Deﬁning a static meth-
od for the experiment is more difﬁcult than for Bernoulli because you cannot
return two values.
 */

 import lib.StdRandom;
 import lib.StdDraw;
 
public class GamblersRuin {

    // Simulates a single gambler's ruin experiment
    public static boolean gamblersRuin(int stake, int goal, int maxBets) {
        int bets = 0;
        int cash = stake;

        while (cash > 0 && cash < goal && bets < maxBets) {
            bets++;
            if (StdRandom.bernoulli(0.5)) {
                cash++;
            } else {
                cash--;
            }
        }

        return cash == goal;
    }

    // Runs multiple experiments and returns the proportion of successful goals
    public static double runExperiments(int stake, int goal, int maxBets, int trials) {
        int successes = 0;

        for (int t = 0; t < trials; t++) {
            if (gamblersRuin(stake, goal, maxBets)) {
                successes++;
            }
        }

        return (double) successes / trials;
    }

    // Plots the results of the experiments
    public static void plotResults(int stake, int goal, int maxBets, int trials) {
        double[] successRates = new double[goal];

        for (int g = 1; g < goal; g++) {
            successRates[g] = runExperiments(stake, g, maxBets, trials);
        }

        StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(0, goal);
        StdDraw.setYscale(0, 1.0);
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLUE);

        for (int g = 1; g < goal; g++) {
            StdDraw.point(g, successRates[g]);
        }
    }

    public static void main(String[] args) {
        int stake = 10;        // Initial stake
        int goal = 50;         // Desired goal
        int maxBets = 1000;    // Maximum number of bets allowed
        int trials = 1000;     // Number of trials to run

        plotResults(stake, goal, maxBets, trials);
    }
}
