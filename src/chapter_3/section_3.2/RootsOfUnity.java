
import lib.Complex;
import lib.StdOut;


/*
 * Write a Complex client RootsOfUnity that takes two double values a and
b and an integer n from the command line and prints the nth roots of a + b i. Note:
Skip this exercise if you are not familiar with the operation of taking roots of com-
plex numbers.
 */



public class RootsOfUnity {

    public static void main(String[] args) {
        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);

        // Calculate the magnitude and argument of the complex number
        double magnitude = Math.sqrt(a * a + b * b);
        double argument = Math.atan2(b, a);

        // Calculate and print each of the nth roots
        StdOut.println("The " + n + " roots of " + a + " + " + b + "i are:");
        for (int k = 0; k < n; k++) {
            double theta = (argument + 2 * Math.PI * k) / n;
            double realPart = Math.pow(magnitude, 1.0 / n) * Math.cos(theta);
            double imagPart = Math.pow(magnitude, 1.0 / n) * Math.sin(theta);
            System.out.println(new Complex(realPart, imagPart));
        }
    }
}
