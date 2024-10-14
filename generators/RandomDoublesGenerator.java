package generators;


import lib.StdOut;
import lib.StdRandom;

public class RandomDoublesGenerator {

    public static void main(String[] args) {
        // Number of random doubles to generate
        int numberOfDoubles = Integer.parseInt(args[0]); 

        // Range for random doubles
        double min = 0.0;
        double max = 1.0;

        // Create a Random object

        // Generate and print random doubles
        for (int i = 0; i < numberOfDoubles; i++) {
            double randomDouble = min + (max - min) * StdRandom.uniformDouble();
            StdOut.println(randomDouble);
        }
    }
}