
import lib.StdOut;
import lib.StdRandom;

/*
 * The Pepys dice problem is a well-known probability puzzle based on a question
 *  posed by Samuel Pepys to Isaac Newton. 
 * The problem involves comparing the probabilities of certain outcomes when rolling dice:
 * Rolling at least one '6' in six rolls of a fair die.
 * Rolling at least two '6's in twelve rolls of a fair die.
 * Rolling at least three '6's in eighteen rolls of a fair die.
 * To solve this problem programmatically, we can use simulation (Monte Carlo method)
 *  to estimate the probabilities. 
 * Below is a Java program to solve the Pepys dice problem by simulating a large number of dice rolls.
 */
public class Pepys {
        public static void main(String[] args) {
            int simulations = 1000000; // Number of simulations for accuracy
    
            double prob1 = calculateProbability(simulations, 6, 1);
            double prob2 = calculateProbability(simulations, 12, 2);
            double prob3 = calculateProbability(simulations, 18, 3);
    
            StdOut.printf("Probability of at least one '6' in 6 rolls: %.5f%n", prob1);
            StdOut.printf("Probability of at least two '6's in 12 rolls: %.5f%n", prob2);
            StdOut.printf("Probability of at least three '6's in 18 rolls: %.5f%n", prob3);
        }
    
        private static double calculateProbability(int simulations, int rolls, int requiredSixes) {
            int successCount = 0;
    
            for (int i = 0; i < simulations; i++) {
                int sixCount = 0;
    
                for (int j = 0; j < rolls; j++) {
                    if (StdRandom.uniformInt(6) + 1 == 6) {
                        sixCount++;
                    }
                }
    
                if (sixCount >= requiredSixes) {
                    successCount++;
                }
            }
    
            return (double) successCount / simulations;
        }
    }
    
