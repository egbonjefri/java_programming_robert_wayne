/*
 * Write a class Horner with a method evaluate() that
takes a ﬂoating-point number x and array p[] as arguments and returns the result
of evaluating the polynomial whose coefﬁcients are the elements in p[] at x:
p(x) = p0 + p1x^1 + p2 x^2 + … + pn_2 x^n-2 + pn_1 x^n-1
Use Horner’s method, an efﬁcient way to perform the computations that is sug-
gested by the following parenthesization:
p(x) = p0 + x (p1 + x (p2 + … + x (p_n-2 + xp_n-1)) . . . )
Write a test client with a static method exp() that uses evaluate() to compute
an approximation to e x, using the ﬁrst n terms of the Taylor series expansion
e x = 1 + x + x 2/2! + x 3/3! + .... Your client should take a command-line argument x
and compare your result against that computed by Math.exp(x).
 */

public class Horner {

    // Method to evaluate a polynomial at a given value x
    public static double evaluate(double x, double[] p) {
        double result = 0;
        for (int i = p.length - 1; i >= 0; i--) {
            result = result * x + p[i]; // Horner's method
        }
        return result;
    }

    // Test client
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]); // Take x from command line argument
        int n = 20; // Number of terms in Taylor series (you can adjust this for precision)
        double[] p = new double[n];

        // Calculate the coefficients for the Taylor series of e^x
        for (int i = n - 1; i >= 0; i--) {
            p[i] = 1.0 / factorial(i);
        }

        double myExp = evaluate(x, p); // Your approximation of e^x
        double realExp = Math.exp(x);  // Real value of e^x from the Java library

        // Output results
        System.out.println("Approximation of e^x: " + myExp);
        System.out.println("Actual value of e^x: " + realExp);
    }

    // Helper method to compute factorial
    public static double factorial(int n) {
        if (n == 0) return 1;
        double fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}
