import lib.StdOut;
/*
 * java program that calculates and
prints the amount of money you would have after t years if you invested P dollars
at an annual interest rate r (compounded continuously). The desired value is given
by the formula Pe^rt.
 */


public class ContinuousCompoundInterest {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java ContinuousCompoundInterest <principal> <interest_rate> <years>");
            return;
        }

        try {
            double principal = Double.parseDouble(args[0]);
            double rate = Double.parseDouble(args[1]);
            int years = Integer.parseInt(args[2]);

            double futureValue = calculateFutureValue(principal, rate, years);
            StdOut.printf("Future value after %d years: $%.2f%n", years, futureValue);

        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid input. Please enter numbers only.");
        }
    }

    private static double calculateFutureValue(double principal, double rate, int years) {
        double exponent = rate * years;
        return principal * Math.exp(exponent); // P * e^(rt)
    }
}
