import lib.StdDraw;
import lib.StdRandom;


public class DiscreteClient {

        public static void main(String[] args) {
            // Define the frequencies for the test
            int[] frequencies = {1, 2, 3, 4};
            
            // Calculate the total sum of frequencies
            int sumFrequencies = 0;
            for (int freq : frequencies) {
                sumFrequencies += freq;
            }
    
            // The number of trials for the stress test
            int trials = 1000000;
    
            // Array to hold the count of times each index is returned
            int[] counts = new int[frequencies.length];
    
            // Perform the trials
            for (int t = 0; t < trials; t++) {
                int index = StdRandom.discrete(frequencies);
                counts[index]++;
            }
            // Set up StdDraw
            StdDraw.setCanvasSize(800, 400);
            StdDraw.setXscale(-1, frequencies.length);
            StdDraw.setYscale(0, 1);  // assuming probabilities won't be more than 1
    
        // Draw the expected probabilities as red bars
        for (int i = 0; i < frequencies.length; i++) {
            double expected = (double) frequencies[i] / sumFrequencies;
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledRectangle(i, expected / 2, 0.3, expected / 2);
        }

        // Overlay the observed frequencies as blue bars
        for (int i = 0; i < frequencies.length; i++) {
            double observed = (double) counts[i] / trials;
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledRectangle(i, observed / 2, 0.25, observed / 2);
        }
 
    }
}

