import java.math.BigInteger;

public class Rational {
    private final BigInteger numerator;
    private final BigInteger denominator;

    // Constructor that ensures the rational number is in reduced form
    public Rational(int num, int den) {
        if (den == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }
        // Reduce the fraction using GCD
        BigInteger gcd = gcd(BigInteger.valueOf(num), BigInteger.valueOf(den));
        this.numerator = BigInteger.valueOf(num).divide(gcd);
        this.denominator = BigInteger.valueOf(den).divide(gcd);

        // Assertions to verify the state
        assert this.denominator.compareTo(BigInteger.ZERO) > 0 : "Denominator should be positive.";
        assert this.numerator.gcd(this.denominator).equals(BigInteger.ONE) : "Numerator and denominator should be relatively prime.";
    }

    // Method to add two rational numbers
    public Rational plus(Rational b) {
        BigInteger newNum = this.numerator.multiply(b.denominator).add(this.denominator.multiply(b.numerator));
        BigInteger newDen = this.denominator.multiply(b.denominator);
        return new Rational(newNum.intValueExact(), newDen.intValueExact());
    }

    // Method to subtract two rational numbers
    public Rational minus(Rational b) {
        BigInteger newNum = this.numerator.multiply(b.denominator).subtract(this.denominator.multiply(b.numerator));
        BigInteger newDen = this.denominator.multiply(b.denominator);
        return new Rational(newNum.intValueExact(), newDen.intValueExact());
    }

    // Method to multiply two rational numbers
    public Rational times(Rational b) {
        BigInteger newNum = this.numerator.multiply(b.numerator);
        BigInteger newDen = this.denominator.multiply(b.denominator);
        return new Rational(newNum.intValueExact(), newDen.intValueExact());
    }

    // Method to divide two rational numbers
    public Rational divides(Rational b) {
        if (b.numerator.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Division by zero.");
        }
        BigInteger newNum = this.numerator.multiply(b.denominator);
        BigInteger newDen = this.denominator.multiply(b.numerator);
        return new Rational(newNum.intValueExact(), newDen.intValueExact());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rational other = (Rational) obj;
        return numerator.equals(other.numerator) && denominator.equals(other.denominator);
    }

    @Override
    public String toString() {
        if (denominator.equals(BigInteger.ONE)) {
            return numerator.toString();
        } else if (numerator.equals(BigInteger.ZERO)) {
            return "0";
        } else {
            return numerator + "/" + denominator;
        }
    }

    private static BigInteger gcd(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) return a;
        return gcd(b, a.mod(b));
    }

    public static void main(String[] args) {
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(2, 3);

        // Test multiplication
        System.out.println(r1 + " times " + r2 + " = " + r1.times(r2));

        // Test division
        System.out.println(r1 + " divided by " + r2 + " = " + r1.divides(r2));

        // Test addition
        System.out.println(r1 + " plus " + r2 + " = " + r1.plus(r2));

        // Test subtraction
        System.out.println(r1 + " minus " + r2 + " = " + r1.minus(r2));

        // Test equality
        Rational r3 = new Rational(2, 4);
        System.out.println(r1 + " equals " + r3 + " = " + r1.equals(r3));

        // Test toString
        System.out.println("String representation of " + r1 + " = " + r1.toString());

        // Additional tests for edge cases
        Rational r4 = new Rational(0, 1);
        System.out.println("Testing rational number with 0 numerator: " + r4);

        try {
            System.out.println(r1 + " divided by " + r4 + " = " + r1.divides(r4));
        } catch (Exception e) {
            System.out.println("Caught exception when trying to divide by zero: " + e.getMessage());
        }
    }
}
