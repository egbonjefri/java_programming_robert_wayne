

/*
 * Implement a data type Rational for rational numbers that supports addi-
tion, subtraction, multiplication, and division.

public class Rational
            Rational(int numerator, int denominator)
Rational    plus(Rational b)        sum of this number and b
Rational    minus(Rational b)       difference of this number and b
Rational    times(Rational b)       product of this number and b
Rational    divides(Rational b)     quotient of this number and b
String      toString()              string representation

Use Euclid.gcd() (PROGRAM 2.3.1) to ensure that the numerator and the denomi-
nator never have any common factors. Include a test client that exercises all of your
methods. Do not worry about testing for integer overﬂow (see EXERCISE 3.3.17).
 */
import lib.StdOut;



public class Rational {
    private int denominator;
    private int numerator;

    public Rational(int num, int den){
        if(den == 0){
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }
        int gcd = gcd(num, den);
        this.numerator = num / gcd;
        this.denominator = den / gcd;
    }
    public Rational times(Rational b){
        int newNum = (this.numerator*b.numerator);
        int newDen = this.denominator * b.denominator;
        return new Rational(newNum, newDen);
    }
    public Rational divides(Rational b){
        int newNum = (this.numerator*b.denominator);
        int newDen = this.denominator * b.numerator;
        return new Rational(newNum, newDen);
    }
    public Rational plus(Rational b){
        int newNum = (this.numerator*b.denominator) + (this.denominator*b.numerator);
        int newDen = this.denominator * b.denominator;
        return new Rational(newNum, newDen);
    }
    public Rational minus(Rational b){
        int newNum = (this.numerator*b.denominator) - (this.denominator*b.numerator);
        int newDen = this.denominator * b.denominator;
        return new Rational(newNum, newDen);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rational other = (Rational) obj;
        return numerator == other.numerator && denominator == other.denominator;
    }
    
    @Override
    public String toString() {
        if (denominator == 1) {
            return String.valueOf(numerator);
        } else if (numerator == 0) {
            return "0";
        } else {
            return numerator + "⁄" + denominator;
        }
    }
    
    private int gcd(int a, int b){
        if(b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(2, 3);

        // Test multiplication
        StdOut.println(r1 + " times " + r2 + " = " + r1.times(r2));

        // Test division
        StdOut.println(r1 + " divided by " + r2 + " = " + r1.divides(r2));

        // Test addition
        StdOut.println(r1 + " plus " + r2 + " = " + r1.plus(r2));

        // Test subtraction
        StdOut.println(r1 + " minus " + r2 + " = " + r1.minus(r2));

        // Test equality
        Rational r3 = new Rational(2, 4); 
        StdOut.println(r1 + " equals " + r3 + " = " + r1.equals(r3));

        // Test toString
        StdOut.println("String representation of " + r1 + " = " + r1.toString());
        
        // Additional tests for edge cases
        Rational r4 = new Rational(0, 1); 
        StdOut.println("Testing rational number with 0 numerator: " + r4);
        
        try {
            StdOut.println(r1 + " divided by " + r4 + " = " + r1.divides(r4));
        } catch (Exception e) {
            StdOut.println("Caught exception when trying to divide by zero: " + e.getMessage());
        }
    }
}
