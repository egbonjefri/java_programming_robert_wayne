import lib.ThreeSum;
import generators.RandomTripleGenerator;
import lib.StdDraw;
import lib.StdRandom;
import lib.StdArrayIO;




public class Threesum{
    public static void visualizeResults(int[] testSizes, double[] counts) {
        double[] scaledTests = StdRandom.scaleArrayToUnitInterval(testSizes);
        double[] scaledCounts = StdRandom.scaleArrayToUnitInterval(counts);
        StdDraw.setXscale(-0.1, 1.1);
        StdDraw.setYscale(-0.1, 1.1);
        // Plot the results
        for (int i = 0; i < testSizes.length; i++) {
            double x = scaledTests[i];
            double y = scaledCounts[i];
            StdDraw.filledCircle(x, y, 0.02); 
        }
    }

    public static void main(String[] args){
        int[] testSizes = {1000, 2000, 3000, 4000, 5000}; 
        
        double[] counts = new double[testSizes.length];
        int trials = 100; 
        for (int i = 0; i < testSizes.length; i++) {
            int n = testSizes[i];
            int count = 0;
            for (int t = 0; t < trials; t++) {
        int[] a = RandomTripleGenerator.generateRandomTriple((n));
        count += ThreeSum.count(a);
           }
           counts[i] = count / (double) trials;
        }
        StdArrayIO.print(counts);
        visualizeResults(testSizes, counts);
    }
}