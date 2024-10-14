/*
 * In 1843, Sir William Hamilton discovered an extension to
complex numbers called quaternions. A quaternion is a 4-tuple a = (a0, a1, a2, a3)
with the following operations:
• Magnitude: |a| = sqrt(a_0^2 + a_1^2 + a_2^2 + a_3^2)

• Conjugate: the conjugate of a is (a0, -a1, -a2, -a3)

• Inverse: a^-1 = (a0 /|a|^2, -a1 /|a|^2, -a2 /|a|^2, -a3 /|a|^2)

• Sum: a + b = (a0 + b0, a1 + b1, a2 + b2, a3 + b3)

• Product: a * b = (a0b0 - a1b1 - a2b2 - a3b3, a0b1 - a1b0 + a2b3 - a3b2,
a0b2 - a1b3 + a2b0 + a3b1, a0b3 + a1b2 - a2b1 + a3b0)

• Quotient: a / b = ab^-1

Create a data type Quaternion for quaternions and a test client that exercises all of
your code. Quaternions extend the concept of rotation in three dimensions to four
dimensions. They are used in computer graphics, control theory, signal processing,
and orbital mechanics.
 */
import lib.StdOut;


public class Quaternion {
    // Components of the quaternion
    //A quaternion is typically represented as q=a+bi+cj+dk
    private double a; // scalar part
    private double b; // coefficient of i
    private double c; // coefficient of j
    private double d; // coefficient of k

    // Constructor to initialize the quaternion components
    public Quaternion(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    // Method to add two quaternions
    public Quaternion add(Quaternion other) {
        return new Quaternion(this.a + other.a, this.b + other.b, this.c + other.c, this.d + other.d);
    }

    // Method to subtract another quaternion from this one
    public Quaternion subtract(Quaternion other) {
        return new Quaternion(this.a - other.a, this.b - other.b, this.c - other.c, this.d - other.d);
    }

    // Method to multiply this quaternion by another quaternion
    public Quaternion multiply(Quaternion other) {
        double newA = this.a * other.a - this.b * other.b - this.c * other.c - this.d * other.d;
        double newB = this.a * other.b + this.b * other.a + this.c * other.d - this.d * other.c;
        double newC = this.a * other.c - this.b * other.d + this.c * other.a + this.d * other.b;
        double newD = this.a * other.d + this.b * other.c - this.c * other.b + this.d * other.a;
        return new Quaternion(newA, newB, newC, newD);
    }

    // Method to calculate the magnitude (norm) of the quaternion
    public double norm() {
        return Math.sqrt(a * a + b * b + c * c + d * d);
    }

    // Method to normalize the quaternion
    public Quaternion normalize() {
        double norm = this.norm();
        return new Quaternion(a / norm, b / norm, c / norm, d / norm);
    }

    // Method to get the conjugate of the quaternion
    public Quaternion conjugate() {
        return new Quaternion(a, -b, -c, -d);
    }

    // Method to compute the inverse of the quaternion
    public Quaternion inverse() {
        double normSquared = this.norm() * this.norm();
        return new Quaternion(a / normSquared, -b / normSquared, -c / normSquared, -d / normSquared);
    }

    // Method to return a string representation of the quaternion
    public String toString() {
        return String.format("%.2f + %.2fi + %.2fj + %.2fk", a, b, c, d);
    }

    // Main method to test the Quaternion ADT
    public static void main(String[] args) {
        Quaternion q1 = new Quaternion(1, 2, 3, 4);
        Quaternion q2 = new Quaternion(2, 3, 4, 1);

        StdOut.println("q1: " + q1);
        StdOut.println("q2: " + q2);
        StdOut.println("Addition: " + q1.add(q2));
        StdOut.println("Subtraction: " + q1.subtract(q2));
        StdOut.println("Multiplication: " + q1.multiply(q2));
        StdOut.println("Norm of q1: " + q1.norm());
        StdOut.println("Normalized q1: " + q1.normalize());
        StdOut.println("Conjugate of q1: " + q1.conjugate());
        StdOut.println("Inverse of q1: " + q1.inverse());
    }
}
