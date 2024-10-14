import lib.StdOut;
import lib.StdRandom;

/*
 * Java program that simulates a gambler's ruin scenario and 
 * prints the number of dollars the gambler has at each step using asterisks
 */
public class GamblerPlot {
    public static void main(String[] args) {
        int initialDollars = 50; // Initial number of dollars
        int goalDollars = 100;   // Goal amount to reach
        int betAmount = 10;      // Amount to bet in each round
        int totalRounds = 0;     // Total number of rounds played

        double probability = 0.25; // 25% probability


        while (initialDollars > 0 && initialDollars < goalDollars) {
            totalRounds++;

            // Simulate a bet (50% chance of winning)
            //boolean win = random.nextBoolean()
            double win = StdRandom.uniformDouble();

            if (win < probability) {
                initialDollars += betAmount;
            } else {
                initialDollars -= betAmount;
            }

            // Ensure the gambler doesn't go below $0
            if (initialDollars < 0) {
                initialDollars = 0;
            }

            // Print the number of dollars using asterisks
            printDollarAmount(initialDollars);
        }

        if (initialDollars == goalDollars) {
            StdOut.println("Gambler reached the goal in " + totalRounds + " rounds!");
        } else {
            StdOut.println("Gambler went broke after " + totalRounds + " rounds.");
        }
    }

    // Method to print dollar amount using asterisks
    private static void printDollarAmount(int dollars) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dollars; i++) {
            sb.append('*');
        }
        StdOut.println(sb.toString());
    }
    public static void analyticalApproach (String[] args) {
        int initialDollars = 50;
        int goalDollars = 100;

        double p = 0.5; // Probability of winning a single bet (50% for a fair game)
        double q = 0.5; // Probability of losing a single bet
        double probability = (1 - Math.pow(q / p, goalDollars - initialDollars)) / (1 - Math.pow(q / p, goalDollars));

        StdOut.println("Probability of reaching the goal: " + probability);
    }
}
