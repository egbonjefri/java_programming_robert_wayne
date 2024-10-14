/*
 * Assume that x is a positive variable of type double.
This is a code fragment that uses the Taylor series expansion to set the value of sum
to e x = 1 + x + x2/2! + x3/3! + . . . .
 */
public class ExponentialFunction {
    public static double e(double x) {
        double sum = 1;
        double term = x;
        int n = 1;
        while (term > 1E-15) {
            term *= x / n;
            sum += term;
            n++;
        }
        return sum;
    }
}
