/*
 * Typically the volatility sigma is the unknown value in the
Black–Scholes formula (see EXERCISE 2.1.28). Write a program that reads s, x, r, t,
and the current price of the European call option from the command line and uses
bisection search to compute sigma
 */

import lib.StdIn;
import lib.StdOut;


public class ImpliedVolatility {
    
    private static final double EPSILON = 1e-6;
    private static final int MAX_ITERATIONS = 100;

    public static void main(String[] args) {

        StdOut.println("Current stock price (s): ");
        double s = StdIn.readDouble();
        StdOut.println("Strike price (x): ");
        double x = StdIn.readDouble();
        StdOut.println("Risk-free interest rate (r): ");
        double r = StdIn.readDouble();
        StdOut.println("Time to expiration in years (t): ");
        double t = StdIn.readDouble();
        StdOut.println("Current price of the European call option (p): ");
        double p = StdIn.readDouble();

        double impliedVolatility = findImpliedVolatility(s, x, r, t, p);
        
        if (impliedVolatility != -1) {
            StdOut.printf("The implied volatility is approximately %.6f\n", impliedVolatility);
        } else {
            StdOut.println("Could not converge to a solution within the maximum number of iterations.");
        }
    }

    private static double findImpliedVolatility(double s, double x, double r, double t, double p) {
        double low = 0.0;
        double high = 5.0;  // Assuming volatility is unlikely to be above 500%

        for (int i = 0; i < MAX_ITERATIONS; i++) {
            double mid = (low + high) / 2;
            double price = blackScholesCallPrice(s, x, r, t, mid);

            if (Math.abs(price - p) < EPSILON) {
                return mid;
            }

            if (price > p) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return -1;  // Failed to converge
    }

    private static double blackScholesCallPrice(double s, double x, double r, double t, double sigma) {
        double d1 = (Math.log(s / x) + (r + sigma * sigma / 2) * t) / (sigma * Math.sqrt(t));
        double d2 = d1 - sigma * Math.sqrt(t);

        return s * normalCDF(d1) - x * Math.exp(-r * t) * normalCDF(d2);
    }

    private static double normalCDF(double x) {
        return 0.5 * (1 + erf(x / Math.sqrt(2)));
    }

    private static double erf(double z) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));

        // Use Horner's method
        double ans = 1 - t * Math.exp(-z * z - 1.26551223 +
                t * (1.00002368 +
                t * (0.37409196 +
                t * (0.09678418 +
                t * (-0.18628806 +
                t * (0.27886807 +
                t * (-1.13520398 +
                t * (1.48851587 +
                t * (-0.82215223 +
                t * (0.17087277))))))))));

        return z >= 0 ? ans : -ans;
    }
}
