
import lib.StdIn;
import lib.StdOut;

public class Quadratic {
    public static void main(String[] args) {

       StdOut.println("Enter a, b, c: ");
        double a = StdIn.readDouble();
        double b = StdIn.readDouble();
        double c = StdIn.readDouble();

        double discriminant = b * b - 4 * a * c;

        if (a == 0) {
            if (b == 0) {
                StdOut.println("The equation is constant.");
            } else {
                StdOut.println("The roots are x = -" + c / b);
            }
        } else if (discriminant < 0) {
            StdOut.println("The equation has no real roots.");
        } else {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            StdOut.println("The roots are x = " + root1 + " and x = " + root2);
        }

    }
}
