import lib.Complex;
import lib.Vector;

public class VectorComplex {
    private Complex value;


    // Constructor taking two double values as arguments
    public VectorComplex(double x, double y) {
        this.value = new Complex(x, y);
    }
    public Complex getValue(){
        return this.value;
    }
    // Add this vector with another vector.
    public VectorComplex plus(VectorComplex that) {
        Complex sum = this.value.plus(that.getValue());
        return new VectorComplex(sum.re(), sum.im());
    }

    // Subtract another vector from this vector.
    public VectorComplex minus(VectorComplex that) {
        Complex diff = this.value.minus(that.getValue());
        return new VectorComplex(diff.re(), diff.im());
    }

    // Scale this vector by a scalar.
    public VectorComplex scale(double alpha) {
        Complex scaled = this.value.scale(alpha);
        return new VectorComplex(scaled.re(), scaled.im());
    }

    // Compute the dot product of this vector with another vector.
    public double dot(VectorComplex that) {
        return this.value.times(that.value.conjugate()).re();
    }

    // Compute the magnitude of this vector.
    public double magnitude() {
        return this.value.abs();
    }

    // Compute the direction (unit vector) of this vector.
    public VectorComplex direction() {
        if (this.magnitude() == 0) throw new ArithmeticException("Zero-vector doesn't have a direction.");
        return this.scale(1 / this.magnitude());
    }

    // Get the x-coordinate of this vector.
    public double getX() {
        return value.re();
    }

    // Get the y-coordinate of this vector.
    public double getY() {
        return value.im();
    }

    // Return a string representation of this vector.
    @Override
    public String toString() {
        return "(" + value.re() + ", " + value.im() + "i)";
    }

    public static void main(String[] args) {
        // Example usage
        Vector vector1 = new Vector(new double[]{1.0, 2.0});
        Vector vector2 = new Vector(new double[]{3.0, 4.0});
        
        // Test operations
        System.out.println("Vector 1: " + vector1);
        System.out.println("Vector 2: " + vector2);
        System.out.println("Vector 1 + Vector 2: " + vector1.plus(vector2));
        System.out.println("Vector 1 - Vector 2: " + vector1.minus(vector2));
        System.out.println("Scaled Vector 1: " + vector1.scale(2.0));
        System.out.println("Dot Product: " + vector1.dot(vector2));
        System.out.println("Magnitude of Vector 1: " + vector1.magnitude());
        System.out.println("Direction of Vector 1: " + vector1.direction());
    }
}
