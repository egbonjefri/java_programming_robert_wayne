/**
 * This function computes the probability of obtaining exactly k heads in n biased coin flips (heads with probability p)
 * using the binomial distribution formula. To prevent numerical overflow, the computation is done in the logarithmic domain.
 * 
 * The standard binomial probability formula is:
 * f(n, k, p) = (n choose k) * p^k * (1-p)^(n-k)
 *            = n! / (k!(n-k)!) * p^k * (1-p)^(n-k)
 * 
 * To avoid overflow when calculating large factorials, we compute the natural logarithm of the probability:
 * ln(f(n, k, p)) = ln(n!) - ln(k!) - ln(n-k!) + k*ln(p) + (n-k)*ln(1-p)
 * 
 * Where:
 * - ln(n!) is the natural log of n factorial and is computed iteratively to prevent overflow.
 * - p is the probability of getting a head in a single coin flip.
 * - k is the number of heads (successes) we want to calculate the probability for.
 * - n is the total number of coin flips.
 * 
 * After computing ln(f(n, k, p)), we use the exp function to convert the result back to the standard probability scale.
 * This method provides a stable and efficient way to compute binomial probabilities for large n and k values.
 * 
 * @param n the number of trials (coin flips)
 * @param k the number of successful trials (heads) we are calculating the probability for
 * @param p the probability of success on a single trial (probability of getting a head)
 * @return the probability of getting exactly k heads in n flips
 */
package lib;


public class BinomialDistribution {

    // Function to calculate ln(n!)
    public static double lnFactorial(int n) {
        double lnFact = 0.0;
        for (int i = 2; i <= n; i++) {
            lnFact += Math.log(i);
        }
        return lnFact;
    }

    // Function to calculate the binomial probability
    public static double binomial(int n, int k, double p) {
        double lnBinomial = lnFactorial(n) - lnFactorial(k) - lnFactorial(n - k)
                            + k * Math.log(p) + (n - k) * Math.log(1 - p);
        // Return the exponent of the calculated natural log
        return Math.exp(lnBinomial);
    }

    // Normal approximation for the binomial distribution
    public static double normalApproximation(int n, int k, double p) {
        double mean = n * p;
        double variance = n * p * (1 - p);
        return (1 / (Math.sqrt(2 * Math.PI * variance))) * 
               Math.exp(-Math.pow(k - mean, 2) / (2 * variance));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]); // number of trials
        double p = Double.parseDouble(args[1]); // probability of success

        double sum = 0.0; // To check if the sum of probabilities is approximately 1
        for (int k = 0; k <= n; k++) {
            double binomialProb = binomial(n, k, p);
            double normalProb = normalApproximation(n, k, p);
            System.out.println("k = " + k + ": Binomial = " + binomialProb + ", Normal = " + normalProb);
            sum += binomialProb;
        }

        System.out.println("Sum of all probabilities (should be ~1): " + sum);
    }
}
