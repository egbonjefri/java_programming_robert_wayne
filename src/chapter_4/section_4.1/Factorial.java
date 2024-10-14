import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import lib.StdDraw;
import lib.StdOut;
import lib.Stopwatch;

public class Factorial {

    public static BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        if (n < 2) {
            return BigInteger.ONE;
        }
        
        List<BigInteger> factors = new ArrayList<>();
        int sqrtN = (int) Math.sqrt(n);
        
        // Group factors
        for (int i = 2; i <= sqrtN; i++) {
            factors.add(BigInteger.valueOf(i));
        }
        for (int i = sqrtN + 1; i <= n; i += sqrtN) {
            BigInteger product = BigInteger.ONE;
            for (int j = i; j < i + sqrtN && j <= n; j++) {
                product = product.multiply(BigInteger.valueOf(j));
            }
            factors.add(product);
        }
        
        // Multiply grouped factors
        while (factors.size() > 1) {
            List<BigInteger> newFactors = new ArrayList<>();
            for (int i = 0; i < factors.size(); i += 2) {
                if (i + 1 < factors.size()) {
                    newFactors.add(factors.get(i).multiply(factors.get(i + 1)));
                } else {
                    newFactors.add(factors.get(i));
                }
            }
            factors = newFactors;
        }
        
        return factors.get(0);
    }
    
    public static int longestConsecutive9s(BigInteger number) {
        String numStr = number.toString();
        int maxRun = 0;
        int currentRun = 0;
        
        for (char c : numStr.toCharArray()) {
            if (c == '9') {
                currentRun++;
                maxRun = Math.max(maxRun, currentRun);
            } else {
                currentRun = 0;
            }
        }
        
        return maxRun;
    }
    
    public static double timeFactorial(int n) {
        Stopwatch timer = new Stopwatch();
        factorial(n);
        return timer.elapsedTime();
    }
    
    public static void main(String[] args) {
        // Analyze performance for various n
        int[] nValues = {10000, 20000, 50000, 100000, 200000, 500000, 1000000};
        double[] times = new double[nValues.length];
        
        for (int i = 0; i < nValues.length; i++) {
            times[i] = timeFactorial(nValues[i]);
            StdOut.printf("n = %d, time = %.3f seconds\n", nValues[i], times[i]);
        }
        
        // Plot results
        StdDraw.setXscale(0, 1100000);
        StdDraw.setYscale(0, times[times.length - 1] * 1.1);
        
        // Draw axes
        StdDraw.line(0, 0, 1100000, 0);
        StdDraw.line(0, 0, 0, times[times.length - 1] * 1.1);
        
        // Plot points
        for (int i = 0; i < nValues.length; i++) {
            StdDraw.setPenRadius(0.01);
            StdDraw.point(nValues[i], times[i]);
        }
        
        StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.RED);
        
        // Calculate the scaleFactor based on the maximum observed time and n value
        double maxTime = times[times.length - 1];
        double maxN = nValues[nValues.length - 1];
        double scaleFactor = maxTime / (maxN * Math.log(maxN) * Math.log(maxN));
        
        for (int x = 1; x <= 1000000; x += 1000) {
            double y = scaleFactor * x * Math.log(x) * Math.log(x);
            StdDraw.point(x, y);
        }
        // Calculate and print 1,000,000!
        int n = 1000000;
        BigInteger result = factorial(n);
        StdOut.println("\nFactorial of " + n + " calculated.");
        StdOut.println("Number of digits: " + result.toString().length());
        StdOut.println("Longest run of consecutive 9s: " + longestConsecutive9s(result));
    }
}