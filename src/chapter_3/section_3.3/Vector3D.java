
/*
 * Implement a data type Vector3D for three-dimensional vectors that has
the same API as Vector, except that the constructor takes three double values as
arguments. Also, add a cross-product method: the cross-product of two vectors is
another vector, deﬁned by the equation
a * b = c |a| |b| sin theta
where c is the unit normal vector perpendicular to both a and b, and theta is the an-
gle between a and b. In Cartesian coordinates, the following equation deﬁnes the
cross-product:
(a0, a1, a2) * (b0, b1, b2) = (a1b2 - a2b1, a2b0 - a0b2, a0b1 - a1b0)
The cross-product arises in the deﬁnition of torque, angular momentum, and vec-
tor operator curl. Also, |a * b| is the area of the parallelogram with sides a and b
 */
import lib.StdDraw;
import java.awt.Color;



public class Vector3D {
    private final double x;
    private final double y;
    private final double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Magnitude
    public double magnitude() {
        return Math.sqrt(this.dotProduct(this));
    }

    // Dot product
    public double dotProduct(Vector3D other) {
        return (this.x * other.x) + (this.y * other.y) + (this.z * other.z);
    }

    // Cross product 
    public Vector3D crossProduct(Vector3D other) {
        double newX = (this.y * other.z) - (this.z * other.y);
        double newY = (this.z * other.x) - (this.x * other.z);
        double newZ = (this.x * other.y) - (this.y * other.x);

        return new Vector3D(newX, newY, newZ);
    }

    // Addition
    public Vector3D add(Vector3D other) {
        return new Vector3D(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    // Subtraction
    public Vector3D subtract(Vector3D other) {
        return new Vector3D(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    // Scalar Multiplication
    public Vector3D scalarMultiply(double scalar) {
        return new Vector3D(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    // Getters 
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    // Helper function to draw a vector with color
    private static void drawVector(Vector3D v, Color color) {
        StdDraw.setPenColor(color);
        StdDraw.line(0, 0, v.getX(), v.getY()); 
        StdDraw.filledCircle(v.getX(), v.getY(), 0.2); 
    }

    public static void main(String[] args) {
        // Setup
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setScale(-10, 10);
    
        Vector3D v1 = new Vector3D(2, 5, 0); 
        Vector3D v2 = new Vector3D(-3, 1, 0);
    
        // Draw the vectors (origin at 0,0)
        drawVector(v1, StdDraw.BOOK_BLUE); 
        drawVector(v2, StdDraw.BOOK_RED);
    
        // Calculate results
        Vector3D cross = v1.crossProduct(v2);
        double magnitudeV1 = v1.magnitude();
    
        // Draw cross product (might need an offset)
        drawVector(cross, StdDraw.PRINCETON_ORANGE); 
    
        // Add labels 
        StdDraw.text(-8, 8, "v1 (blue)");
        StdDraw.text(-8, 7,  "v2 (red)");
        StdDraw.text(-8, 6,  "Cross Product (orange)");
        StdDraw.text(-8, 5,  "Magnitude of v1: " + magnitudeV1);
    }
    

}
