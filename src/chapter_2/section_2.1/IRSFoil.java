import lib.StdIn;
import lib.StdRandom;
public class IRSFoil {
           // Function to generate random amounts with observed distribution
    public static void generateRandomAmounts(int[] counts) {
        int totalCounts = 0;
        for (int count : counts) {
            totalCounts += count;
        }

        for (int i = 0; i < 100; i++) { // Generate 100 random amounts for demonstration
            double amount = 1 + 999 * StdRandom.uniformDouble(); // $1.00 to $1,000.00
            int leadingDigit = leadingDigit((int)amount);
            double probability = (double)counts[leadingDigit] / totalCounts;
            
            // Adjust amount to fit the observed distribution
            if (StdRandom.uniformDouble() < probability) {
                System.out.printf("$%.2f\n", amount);
            }
        }
    }
    public static int leadingDigit(int number) {
        // ensure the number is positive and a single digit
        number = Math.abs(number); 
        while (number >= 10) {
            number /= 10;
        }
        return number;
    }

    
        public static void main(String[] args) {
            int[] observedCounts = new int[10]; // Array for counts
    
            for (int i = 1; i < observedCounts.length; i++) {
                if (!StdIn.isEmpty()) {
                    observedCounts[i] = StdIn.readInt();
                }
            }
    
            generateRandomAmounts(observedCounts);
        }
}
