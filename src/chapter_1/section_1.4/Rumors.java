import lib.StdRandom;
/*
 * Alice is throwing a party with n other guests, including Bob. Bob
starts a rumor about Alice by telling it to one of the other guests. A person hear-
ing this rumor for the ﬁrst time will immediately tell it to one other guest, chosen
uniformly at random from all the people at the party except Alice and the person
from whom they heard it. If a person (including Bob) hears the rumor for a second
time, he or she will not propagate it further. Write a program to estimate the prob-
ability that everyone at the party (except Alice) will hear the rumor before it stops
propagating. Also calculate an estimate of the expected number of people to hear
the rumor.
 */
public class Rumors {
    public static void main(String[] args) {
        int numTrials = 100000;  // Number of simulations
        int n = 10;              // Number of guests besides Alice and Bob

        int everyoneHeardCount = 0;
        int totalPeopleHeard = 0;


        for (int trial = 0; trial < numTrials; trial++) {
            boolean[] heardRumor = new boolean[n + 1]; // Bob + n guests
            heardRumor[0] = true; // Bob starts the rumor

            int peopleHeard = 1;  // Bob has already heard it

            while (true) {
                int tellerIndex = StdRandom.uniformInt(n + 1); // Choose a random person
                while (heardRumor[tellerIndex]) {         // Ensure they haven't heard it
                    tellerIndex = StdRandom.uniformInt(n + 1);
                }

                int receiverIndex = StdRandom.uniformInt(n + 1);
                while (receiverIndex == tellerIndex || heardRumor[receiverIndex]) {
                    receiverIndex = StdRandom.uniformInt(n + 1);
                }

                heardRumor[receiverIndex] = true;
                peopleHeard++;

                if (everyoneHeard(heardRumor)) {
                    everyoneHeardCount++;
                    break;
                }

                if (rumorStopped(heardRumor)) {
                    break;
                }
            }

            totalPeopleHeard += peopleHeard;
        }

        double probabilityEveryoneHears = (double) everyoneHeardCount / numTrials;
        double expectedPeopleHeard = (double) totalPeopleHeard / numTrials;

        System.out.printf("Probability everyone hears the rumor: %.4f%n", probabilityEveryoneHears);
        System.out.printf("Expected number of people to hear the rumor: %.2f%n", expectedPeopleHeard);
    }

    private static boolean everyoneHeard(boolean[] heardRumor) {
        for (boolean heard : heardRumor) {
            if (!heard) return false;
        }
        return true;
    }

    private static boolean rumorStopped(boolean[] heardRumor) {
        for (int i = 0; i < heardRumor.length; i++) {
            if (!heardRumor[i]) {
                for (int j = 0; j < heardRumor.length; j++) {
                    if (i != j && !heardRumor[j]) {
                        return false; // At least one pair hasn't heard it yet
                    }
                }
            }
        }
        return true;
    }
}
