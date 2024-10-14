import lib.StdOut;
/*
 * Write a program that prints a table of the monthly payments, remaining
principal, and interest paid for a loan, taking three numbers as command-line
arguments: the number of years, the principal, and the interest rate (see EXER-
CISE 1.2.24).
 */
public class LoanCalculator {
    public static void main(String[] args) {
        // Check if three command-line arguments are provided
        if (args.length != 3) {
            System.out.println("Usage: java LoanCalculator <number_of_years> <principal> <interest_rate>");
            System.exit(1);
        }

        // Parse command-line arguments
        int numberOfYears = Integer.parseInt(args[0]);
        double principal = Double.parseDouble(args[1]);
        double interestRate = Double.parseDouble(args[2]);

        // Monthly interest rate
        double monthlyInterestRate = interestRate / 12 / 100;

        // Total number of payments
        int numberOfPayments = numberOfYears * 12;

        // Calculate monthly payment
        double monthlyPayment = (principal * monthlyInterestRate) /
                (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));

        // Print table header
       StdOut.printf("%-15s%-20s%-20s%-20s\n", "Payment#", "Monthly Payment", "Remaining Principal", "Interest Paid");

        // Calculate and print the table for each payment
        for (int paymentNumber = 1; paymentNumber <= numberOfPayments; paymentNumber++) {
            // Calculate interest paid for this payment
            double interestPaid = principal * monthlyInterestRate;

            // Calculate remaining principal after this payment
            double remainingPrincipal = principal - (monthlyPayment - interestPaid);

            // Print the table row
           StdOut.printf("%-15d%-20.2f%-20.2f%-20.2f\n", paymentNumber, monthlyPayment, remainingPrincipal, interestPaid);

            // Update principal for the next iteration
            principal = remainingPrincipal;
        }
    }
}
