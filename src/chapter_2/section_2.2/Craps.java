/*
 * The following are the rules for a pass bet in the game of craps. Roll
two six-sided dice, and let x be their sum.
• If x is 7 or 11, you win.
• If x is 2, 3, or 12, you lose.
Otherwise, repeatedly roll the two dice until their sum is either x or 7.
• If their sum is x, you win.
• If their sum is 7, you lose.
Write a modular program to estimate the probability of winning a pass bet. Modify
your program to handle loaded dice, where the probability of a die landing on 1
is taken from the command line, the probability of landing on 6 is 1/6 minus that
probability, and 2–5 are assumed equally likely. Hint : Use StdRandom.discrete().
 */


 import lib.StdOut;
 import lib.StdRandom;

 
 public class Craps {

    public static void main(String[] args) {
        int trials = 1000000; // Number of simulations
        double probOfOne = args.length > 0 ? Double.parseDouble(args[0]) : 1.0 / 6.0;
        double probOfSix = 1.0 / 6.0 - probOfOne;
        double probOfTwoToFive = (1.0 - probOfOne - probOfSix) / 4.0;

        // Probabilities for each side of the die
        double[] probabilities = new double[]{probOfOne, probOfTwoToFive, probOfTwoToFive, probOfTwoToFive, probOfTwoToFive, probOfSix};

        int wins = 0;
        for (int t = 0; t < trials; t++) {
            if (playCraps(probabilities)) {
                wins++;
            }
        }

        StdOut.println("Probability of winning: " + (double) wins / trials);
    }

    // Simulate a single game of craps
    private static boolean playCraps(double[] probabilities) {
        int x = rollDice(probabilities);
        if (x == 7 || x == 11) return true;
        if (x == 2 || x == 3 || x == 12) return false;
        int point = x;
        while (true) {
            x = rollDice(probabilities);
            if (x == point) return true;
            if (x == 7) return false;
        }
    }

    // Roll two dice with given probabilities and return their sum
    private static int rollDice(double[] probabilities) {
        int die1 = StdRandom.discrete(probabilities) + 1;
        int die2 = StdRandom.discrete(probabilities) + 1;
        return die1 + die2;
    }
}
