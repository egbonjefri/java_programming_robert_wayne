import lib.StdRandom;
import lib.StdOut;
/*
 * Suppose that people enter an empty room until a pair
of people share a birthday. On average, how many people will have to enter before
there is a match? Run experiments to estimate the value of this quantity. Assume
birthdays to be uniform random integers between 0 and 364.
 */
public class BirthdayParadox {
    public static void main(String[] args) {
        int trials = 10000;  
        int totalPeople = 0;

        for (int t = 0; t < trials; t++) {
            boolean[] birthdays = new boolean[365];
            int people = 0;
            boolean foundMatch = false;
            
            while (!foundMatch) {
                int birthday = StdRandom.uniformInt(365);  
                people++;
                
                if (birthdays[birthday]) { 
                    foundMatch = true;
                } else {
                    birthdays[birthday] = true;
                }
            }
            totalPeople += people;
        }

        double average = (double) totalPeople / trials;
        StdOut.println("Average number of people needed: " + average);
    }
}