
/*
 * Write a library of static methods that implements the hyperbolic functions
based on the deﬁnitions sinh(x) = (e^x - e^(-x)) / 2, cosh(x) = (e^x + e^(-x)) / 2, with
tanh(x), coth(x), sech(x), and csch(x) deﬁned in a manner analogous to standard
trigonometric functions.
 */

public class Hyperbolic {

    // sinh(x) = (e^x - e^(-x)) / 2
    public static double sinh(double x) {
        return (Math.exp(x) - Math.exp(-x)) / 2;
    }

    // cosh(x) = (e^x + e^(-x)) / 2
    public static double cosh(double x) {
        return (Math.exp(x) + Math.exp(-x)) / 2;
    }

    // tanh(x) = sinh(x) / cosh(x)
    public static double tanh(double x) {
        return sinh(x) / cosh(x);
    }

    // coth(x) = 1 / tanh(x) = cosh(x) / sinh(x)
    // Note: coth(x) is undefined for x = 0
    public static double coth(double x) {
        if (x == 0) {
            throw new ArithmeticException("Value of coth(x) is undefined for x = 0.");
        }
        return 1.0 / tanh(x);
    }

    // sech(x) = 1 / cosh(x)
    public static double sech(double x) {
        return 1.0 / cosh(x);
    }

    // csch(x) = 1 / sinh(x)
    // Note: csch(x) is undefined for x = 0
    public static double csch(double x) {
        if (x == 0) {
            throw new ArithmeticException("Value of csch(x) is undefined for x = 0.");
        }
        return 1.0 / sinh(x);
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test values
        double x = 1.0;
        System.out.println("sinh(" + x + ") = " + sinh(x));
        System.out.println("cosh(" + x + ") = " + cosh(x));
        System.out.println("tanh(" + x + ") = " + tanh(x));
        System.out.println("coth(" + x + ") = " + coth(x));
        System.out.println("sech(" + x + ") = " + sech(x));
        System.out.println("csch(" + x + ") = " + csch(x));
    }
}
