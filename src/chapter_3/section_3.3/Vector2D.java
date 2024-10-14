/*
 * Implement a data type Vector2D for two-dimensional vectors that has the
same API as Vector, except that the constructor takes two double values as argu-
ments. Use two double values (instead of an array) for instance variables.
 */
import lib.StdOut;



public class Vector2D {
    private double x;
    private double y;

    // Constructor taking two double values as arguments
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Add this vector with another vector.
    public Vector2D plus(Vector2D that) {
        return new Vector2D(this.x + that.x, this.y + that.y);
    }

    // Subtract another vector from this vector.
    public Vector2D minus(Vector2D that) {
        return new Vector2D(this.x - that.x, this.y - that.y);
    }

    // Scale this vector by a scalar.
    public Vector2D scale(double alpha) {
        return new Vector2D(this.x * alpha, this.y * alpha);
    }

    // Compute the dot product of this vector with another vector.
    public double dot(Vector2D that) {
        return this.x * that.x + this.y * that.y;
    }

    // Compute the magnitude of this vector.
    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    // Compute the direction (unit vector) of this vector.
    public Vector2D direction() {
        if (this.magnitude() == 0) throw new ArithmeticException("Zero-vector doesn't have a direction.");
        return this.scale(1 / this.magnitude());
    }
    // Compute the Euclidean distance between this vector and another vector.
    public double distanceTo(Vector2D that) {
        double dx = that.x - this.x;
        double dy = that.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Get the x-coordinate of this vector.
    public double getX() {
        return x;
    }

    // Get the y-coordinate of this vector.
    public double getY() {
        return y;
    }

    // Return a string representation of this vector.
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // main method for testing
    public static void main(String[] args) {
        Vector2D vector1 = new Vector2D(1.0, 2.0);
        Vector2D vector2 = new Vector2D(4.0, 5.0);
        
    
        StdOut.println("Distance: " + vector1.distanceTo(vector2));
    }
}
