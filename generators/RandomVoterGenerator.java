package generators;
import lib.StdOut;
import lib.StdRandom;


public class RandomVoterGenerator {
    private static final String[] FIRST_NAMES = {"Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Henry", "Ivy", "Jack"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};

    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("Usage: java RandomVoterGenerator <numberOfVoters>");
            return;
        }

        int numberOfVoters = Integer.parseInt(args[0]);

        // Generate and print random voters
        for (int i = 0; i < numberOfVoters; i++) {
            String voter = generateRandomVoter();
            StdOut.println(voter);  
        }
    }

    private static String generateRandomVoter() {
        String firstName = FIRST_NAMES[StdRandom.uniformInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[StdRandom.uniformInt(LAST_NAMES.length)];
        int voterID = 1000 + StdRandom.uniformInt(9000); // Voter ID between 1000 and 9999

        return firstName + " " + lastName + " - ID: " + voterID;
    }
}
