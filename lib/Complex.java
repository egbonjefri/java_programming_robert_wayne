package lib;
/******************************************************************************
 *  Compilation:  javac Complex.java
 *  Execution:    java Complex
 *
 *  Data type for complex numbers.
 *
 *  The data type is "immutable" so once you create and initialize
 *  a Complex object, you cannot change it. The "final" keyword
 *  when declaring re and im enforces this rule, making it a
 *  compile-time error to change the .re or .im instance variables after
 *  they've been initialized.
 *
 *  % java Complex
 *  a            = 5.0 + 6.0i
 *  b            = -3.0 + 4.0i
 *  Re(a)        = 5.0
 *  Im(a)        = 6.0
 *  b + a        = 2.0 + 10.0i
 *  a - b        = 8.0 + 2.0i
 *  a * b        = -39.0 + 2.0i
 *  b * a        = -39.0 + 2.0i
 *  a / b        = 0.36 - 1.52i
 *  (a / b) * b  = 5.0 + 6.0i
 *  conj(a)      = 5.0 - 6.0i
 *  |a|          = 7.810249675906654
 *  tan(a)       = -6.685231390246571E-6 + 1.0000103108981198i
 *
 ******************************************************************************/



import java.util.Objects;

 public class Complex {
     private final double re;   // the real part
     private final double im;   // the imaginary part
 
     // create a new object with the given real and imaginary parts
     public Complex(double real, double imag) {
         re = real;
         im = imag;
     }
/*
 * Modify the toString() method in Complex (PROGRAM 3.2.6) so that it
prints complex numbers in the traditional format. For example, it should print the
value 3 - i as 3 - i instead of 3.0 + -1.0i, the value 3 as 3 instead of 3.0 + 0.0i,
and the value 3i as 3i instead of 0.0 + 3.0i.
 */
public String toString() {
    // Format real and imaginary parts to avoid unnecessary decimal points.
    String real = (re == (int)re) ? String.format("%d", (int)re) : String.format("%s", re);
    String imag = (Math.abs(im) == (int)Math.abs(im)) ? String.format("%d", (int)Math.abs(im)) : String.format("%s", Math.abs(im));

    // Handle different cases based on the values of real and imaginary parts.
    if (im == 0) return real; // Only real part is non-zero.
    if (re == 0) {
        if (im == 1) return "i"; // Pure imaginary positive unit.
        if (im == -1) return "-i"; // Pure imaginary negative unit.
        return imag + "i"; // Only imaginary part is non-zero.
    }
    if (im < 0) {
        if (im == -1) return real + " - i"; // Avoid displaying "-1i".
        return real + " - " + imag + "i"; // Negative imaginary part.
    }
    if (im == 1) return real + " + i"; // Positive imaginary unit.
    return real + " + " + imag + "i"; // Positive imaginary part.
}

     // return abs/modulus/magnitude
     public double abs() {
         return Math.hypot(re, im);
     }
 
     // return angle/phase/argument, normalized to be between -pi and pi
     public double phase() {
         return Math.atan2(im, re);
     }
 
     // return a new Complex object whose value is (this + b)
     public Complex plus(Complex b) {
         Complex a = this;             // invoking object
         double real = a.re + b.re;
         double imag = a.im + b.im;
         return new Complex(real, imag);
     }
 
     // return a new Complex object whose value is (this - b)
     public Complex minus(Complex b) {
         Complex a = this;
         double real = a.re - b.re;
         double imag = a.im - b.im;
         return new Complex(real, imag);
     }
 
     // return a new Complex object whose value is (this * b)
     public Complex times(Complex b) {
         Complex a = this;
         double real = a.re * b.re - a.im * b.im;
         double imag = a.re * b.im + a.im * b.re;
         return new Complex(real, imag);
     }
 
     // return a new object whose value is (this * alpha)
     public Complex scale(double alpha) {
         return new Complex(alpha * re, alpha * im);
     }
 
     // return a new Complex object whose value is the conjugate of this
     public Complex conjugate() {
         return new Complex(re, -im);
     }
 
     // return a new Complex object whose value is the reciprocal of this
     public Complex reciprocal() {
         double scale = re*re + im*im;
         return new Complex(re / scale, -im / scale);
     }
 
     // return the real or imaginary part
     public double re() { return re; }
     public double im() { return im; }
 
     // return a / b
     public Complex divides(Complex b) {
         Complex a = this;
         return a.times(b.reciprocal());
     }
 
     // return a new Complex object whose value is the complex exponential of this
     public Complex exp() {
         return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
     }
 
     // return a new Complex object whose value is the complex sine of this
     public Complex sin() {
         return new Complex(Math.sin(re) * Math.cosh(im), Math.cos(re) * Math.sinh(im));
     }
 
     // return a new Complex object whose value is the complex cosine of this
     public Complex cos() {
         return new Complex(Math.cos(re) * Math.cosh(im), -Math.sin(re) * Math.sinh(im));
     }
 
     // return a new Complex object whose value is the complex tangent of this
     public Complex tan() {
         return sin().divides(cos());
     }
 
 
 
     // a static version of plus
     public static Complex plus(Complex a, Complex b) {
         double real = a.re + b.re;
         double imag = a.im + b.im;
         Complex sum = new Complex(real, imag);
         return sum;
     }
 
     // See Section 3.3.
     public boolean equals(Object x) {
         if (x == null) return false;
         if (this.getClass() != x.getClass()) return false;
         Complex that = (Complex) x;
         return (this.re == that.re) && (this.im == that.im);
     }
 
     // See Section 3.3.
     public int hashCode() {
         return Objects.hash(re, im);
     }
 //Write a Complex client that takes three ﬂoating-point numbers a, b, and
// c as command-line arguments and prints the two (complex) roots of ax2 + bx + c.
     public static void main(String[] args) {
        // Parse command-line arguments as doubles
        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        double c = Double.parseDouble(args[2]);

        // Calculate the discriminant
        double discriminant = b * b - 4 * a * c;

        // Calculate the two roots
        Complex root1, root2;
        if (discriminant >= 0) {
            // Real roots
            double realPart = -b / (2 * a);
            double imaginaryPart = Math.sqrt(discriminant) / (2 * a);
            root1 = new Complex(realPart + imaginaryPart, 0);
            root2 = new Complex(realPart - imaginaryPart, 0);
        } else {
            // Complex roots
            double realPart = -b / (2 * a);
            double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
            root1 = new Complex(realPart, imaginaryPart);
            root2 = new Complex(realPart, -imaginaryPart);
        }

        // Print the roots
        StdOut.println("Root 1: " + root1);
        StdOut.println("Root 2: " + root2);
    }
 
 }
 