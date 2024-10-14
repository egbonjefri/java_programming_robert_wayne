import java.util.HashSet;
import lib.StdRandom;;
/*
 * Develop a class with appropriate static methods for
studying the birthday problem
 */
public class BirthdayProblem {

    // Method to simulate one experiment of the birthday problem
    public static boolean hasSharedBirthday(int numPeople) {
        // Using a HashSet to track seen birthdays
        HashSet<Integer> birthdays = new HashSet<>();

        for (int i = 0; i < numPeople; i++) {
            int birthday = StdRandom.uniformInt(365); // Random birthday between 0 and 364 (inclusive)
            if (birthdays.contains(birthday)) {
                return true; // Found a shared birthday
            }
            birthdays.add(birthday);
        }
        return false; // No shared birthdays found
    }

    // Method to estimate the probability of a shared birthday for a given number of people
    public static double estimateProbability(int numPeople, int numTrials) {
        int sharedBirthdayCount = 0;

        for (int i = 0; i < numTrials; i++) {
            if (hasSharedBirthday(numPeople)) {
                sharedBirthdayCount++;
            }
        }
        return (double) sharedBirthdayCount / numTrials;
    }

    // Method to find the smallest number of people needed for a given probability threshold
    public static int findSmallestGroup(double threshold, int numTrials) {
        int numPeople = 1;
        while (true) {
            double probability = estimateProbability(numPeople, numTrials);
            if (probability >= threshold) {
                return numPeople;
            }
            numPeople++;
        }
    }

    public static void main(String[] args) {
        // Example usage
        int numPeople = 23;
        int numTrials = 10000;
        double threshold = 0.5;

        double probability = estimateProbability(numPeople, numTrials);
        System.out.println("Estimated probability of at least one shared birthday in a group of " + numPeople + " people: " + probability);

        int smallestGroup = findSmallestGroup(threshold, numTrials);
        System.out.println("Smallest number of people needed for a probability of at least " + threshold + ": " + smallestGroup);
    }
}
