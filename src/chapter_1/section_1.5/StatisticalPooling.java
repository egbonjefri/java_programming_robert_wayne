/*
 * When collecting statistical data for certain political polls,
it is very important to obtain an unbiased sample of registered voters. Assume that
you have a ﬁle with n registered voters, one per line. Write a ﬁlter that prints a uni-
formly random sample of size m
 */
import lib.StdOut;
import lib.StdIn;
import lib.StdRandom;

public class StatisticalPooling {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java RandomVoterSampler <sampleSize>");
            return;
        }

        int sampleSize = Integer.parseInt(args[0]);

        // Read voters from standard input
        String[] voters = readVoters();

        // Ensure the sample size is not greater than the total number of voters
        if (sampleSize > voters.length) {
            StdOut.println("Sample size cannot be greater than the total number of voters.");
            return;
        }

        // Randomly sample voters
        String[] sampledVoters = getRandomSample(voters, sampleSize);

        // Print the sampled voters
        for (String voter : sampledVoters) {
            StdOut.println(voter);
        }
    }

    private static String[] readVoters() {
        // Read voters from standard input
        String[] voters = StdIn.readAllLines();
        return voters;
    }

    private static String[] getRandomSample(String[] voters, int sampleSize) {
        String[] sampledVoters = new String[sampleSize];

        // Create an array of indices
        int[] indices = new int[voters.length];
        for (int i = 0; i < voters.length; i++) {
            indices[i] = i;
        }

        // Randomly shuffle the indices
        for (int i = indices.length - 1; i > 0; i--) {
            int j = StdRandom.uniformInt(i + 1);
            int temp = indices[i];
            indices[i] = indices[j];
            indices[j] = temp;
        }

        // Take the first 'sampleSize' elements
        for (int i = 0; i < sampleSize; i++) {
            sampledVoters[i] = voters[indices[i]];
        }

        return sampledVoters;
    }
}
