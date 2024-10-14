import lib.StdOut;
/*
 * Run experiments to validate the classical mathematical
result that the expected number of coupons needed to collect n values is approxi-
mately n Hn, where Hn in the nth harmonic number. For example, if you are ob-
serving the cards carefully at the blackjack table (and the dealer has enough decks
randomly shufﬂed together), you will wait until approximately 235 cards are dealt,
on average, before seeing every card value.
 */
public class CouponCollector {

    // Function to calculate the nth harmonic number
    private static double harmonicNumber(int n) {
        double harmonic = 0;
        for (int i = 1; i <= n; i++) {
            harmonic += 1.0 / i;
        }
        return harmonic;
    }

    // Function to simulate the coupon collector's experiment
    private static int collectCoupons(int n) {
        boolean[] collected = new boolean[n];
        int count = 0, distinct = 0;

        while (distinct < n) {
            int coupon = (int) (Math.random() * n); // Random coupon
            count++;
            if (!collected[coupon]) {
                distinct++;
                collected[coupon] = true;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int n = 1000; // Number of different coupons
        int trials = 10000; // Number of trials to run

        double expectedCoupons = n * harmonicNumber(n);
        double totalCoupons = 0;

        for (int i = 0; i < trials; i++) {
            totalCoupons += collectCoupons(n);
        }

        double averageCoupons = totalCoupons / trials;

       StdOut.println("Expected number of coupons (n * Hn): " + expectedCoupons);
       StdOut.println("Average number of coupons collected in " + trials + " trials: " + averageCoupons);
    }
}
