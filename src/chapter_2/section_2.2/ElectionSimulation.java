/*
 * Develop a StdRandom client (with appropriate static
methods of its own) to study the following problem: Suppose that in a popula-
tion of 100 million voters, 51% vote for candidate A and 49% vote for candidate
B. However, the voting machines are prone to make mistakes, and 5% of the time
they produce the wrong answer. Assuming the errors are made independently and
at random, is a 5% error rate enough to invalidate the results of a close election?
What error rate can be tolerated?
 */

import lib.StdRandom;


public class ElectionSimulation {

    public static void main(String[] args) {
        int totalVoters = 100_000_000;
        double percentForA = 0.51;
        double errorRate = 0.05; // Start with 5% error rate

        int numberOfSimulations = 1000; // Number of times to run the simulation
        int alteredElections = 0; // Count of how many times the result changes

        for (int i = 0; i < numberOfSimulations; i++) {
            if (simulateElection(totalVoters, percentForA, errorRate)) {
                alteredElections++;
            }
        }

        System.out.println("Number of Altered Elections: " + alteredElections);
        System.out.println("Percentage of Altered Elections: " + (double) alteredElections / numberOfSimulations * 100 + "%");
    }

    private static boolean simulateElection(int totalVoters, double percentForA, double errorRate) {
        int effectiveVotesForA = 0;

        for (int i = 0; i < totalVoters; i++) {
            if (StdRandom.bernoulli(percentForA)) { // Voter intends to vote for A
                effectiveVotesForA += StdRandom.bernoulli(1 - errorRate) ? 1 : 0; // Count vote for A or error
            } else { // Voter intends to vote for B
                effectiveVotesForA += StdRandom.bernoulli(errorRate) ? 1 : 0; // Count error as vote for A
            }
        }

        return effectiveVotesForA < totalVoters / 2; // Check if A loses
    }
}
