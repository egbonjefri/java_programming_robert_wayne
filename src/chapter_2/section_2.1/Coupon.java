import lib.StdOut;
import lib.BinomialDistribution;
/*
 * Develop a version of getCoupon() that better models the situation when
one of the coupons is rare: choose one of the n values at random, return that value
with probability 1 /(1,000n), and return all other values with equal probability. Ex-
tra credit : How does this change affect the expected number of coupons that need
to be collected in the coupon collector problem?
 */

public class Coupon{
        private static double probability; // Probability for the rare coupon, calculated from n
        private static int rareCoupon; // The rare coupon, chosen once and
    // Generates a random integer between 0 and n-1, with one rare coupon
    public static int getCoupon(int n) {
        // Decide whether to return the rare coupon or a common one
        if (Math.random() < probability) {
            // Return the rare coupon with probability 1/(1000n)
            return rareCoupon;
        } else {
            // Return any other coupon with equal probability
            return (int) (Math.random() * n);
        }
    }
        // Method to find the smallest k for which the sum of f(n, j, 1/2) for all j < k exceeds x
        public static int couponBinomial(int n) {
            double p = 0.5; // Probability for binomial distribution
            double x = Math.random(); // Uniformly random number between 0 and 1
            double sum = 0.0;
            int k = 0;
    
            while (sum < x && k < n) {
                sum += BinomialDistribution.binomial(n, k, p);
                k++;
            }
    
            return k; // Returns the smallest k for which the sum exceeds x
        }
            public static int collectCoupons(int n){
             // Collect coupons until getting one of each value
               // and return the number of coupons collected.
                boolean[] isCollected = new boolean[n];
                int count = 0, distinct = 0;
                while (distinct < n){
                int r = getCoupon(n);
                count++;
                if (!isCollected[r]) distinct++;
                isCollected[r] = true;
                    }
             return count;
}
        public static void main(String[] args){
        // Ensure that an argument is provided
        if(args.length < 1) {
            System.out.println("Usage: java Coupon <n>");
            System.exit(1);
        }
             // Collect n different coupons.
        int n = Integer.parseInt(args[0]);
        probability = 1.0 / (1000 * n); // Calculate the probability based on n
        rareCoupon = (int) (Math.random() * n); // Choose the rare coupon
        int count = collectCoupons(n);
        StdOut.println(count);
        }
    }







   