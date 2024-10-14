/**
 * This program calculates the Black-Scholes value for a European call option.
 * The Black-Scholes formula is:
 *
 * C(S, t) = S_0 * Φ(d1) - X * e^(-rt) * Φ(d2)
 *
 * where:
 * - C(S, t) is the price of the European call option.
 * - S_0 is the current price of the stock (the variable `s` in the program).
 * - X is the strike price of the option (the variable `x` in the program, assumed to be 100).
 * - r is the risk-free interest rate.
 * - t is the time to maturity of the option.
 * - Φ(d) is the cumulative distribution function of the standard normal distribution.
 * - d1 = (ln(S_0/X) + (r + σ^2/2)*t) / (σ*sqrt(t))
 * - d2 = d1 - σ*sqrt(t)
 * - σ is the volatility of the stock's returns.
 *
 * Usage:
 * Compile: javac BlackScholes.java
 * Run: java BlackScholes <current stock price> <risk-free interest rate> <volatility> <time to maturity>
 * Example: java BlackScholes 150 0.01 0.25 1
 *
 * This command calculates the Black-Scholes value for a stock currently priced at 150,
 * with a 1% risk-free interest rate, 25% volatility, and 1 year to maturity.
 */
public class BlackScholes {
         // Implement Gaussian (normal) distribution functions.
         public static double pdf(double x){
            return Math.exp(-x*x/2) / Math.sqrt(2*Math.PI);
                }
 // Gaussian cumulative distribution function (CDF) approximation
 public static double cdf(double z) {
    if (z < -8.0) return 0.0;
    if (z >  8.0) return 1.0;
    double sum = 0.0, term = z;
    for (int i = 3; sum + term != sum; i += 2) {
        sum  = sum + term;
        term = term * z * z / i;
    }
    return 0.5 + pdf(z) * sum;
}

// Black-Scholes formula
public static double blackScholes(double s, double x, double r, double sigma, double t) {
    double d1 = (Math.log(s/x) + (r + sigma*sigma/2) * t) / (sigma * Math.sqrt(t));
    double d2 = d1 - sigma * Math.sqrt(t);
    return s * cdf(d1) - x * Math.exp(-r*t) * cdf(d2);
}

public static void main(String[] args) {
    // Ensure correct number of arguments are passed
    if(args.length < 4) {
        System.out.println("Usage: java BlackScholes <s> <r> <sigma> <t>");
        System.exit(1);
    }
    
    // Parse command-line arguments
    double s = Double.parseDouble(args[0]);     // Current stock price
    double r = Double.parseDouble(args[1]);     // Risk-free interest rate
    double sigma = Double.parseDouble(args[2]); // Volatility
    double t = Double.parseDouble(args[3]);     // Time to maturity

    // Calculate and print the Black-Scholes value
    double value = blackScholes(s, 100, r, sigma, t); // assuming exercise price (x) is 100
    System.out.println("The Black-Scholes value is: " + value);
}
}
