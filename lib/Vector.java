package lib;

import java.util.Objects;


public class Vector implements Comparable<Vector> {
    private final double[] coordinates;

    // Construct a vector from an array of doubles.
    public Vector(double[] a) {
        // Defensive copy to ensure immutability of the vector
        coordinates = new double[a.length];
        System.arraycopy(a, 0, coordinates, 0, a.length);
    }

    // Add this vector with another vector.
    public Vector plus(Vector that) {
        if (this.coordinates.length != that.coordinates.length){
            throw new IllegalArgumentException("Dimensions disagree.");
        }
        double[] result = new double[this.coordinates.length];
        for (int i = 0; i < this.coordinates.length; i++) {
            result[i] = this.coordinates[i] + that.coordinates[i];
        }
        return new Vector(result);
    }
    public Vector perp() {
        // In 2D, a perpendicular vector is obtained by swapping x and y, 
        // and negating one of them.
        return new Vector(new double[]{-this.cartesian(1), this.cartesian(0)}); 
    }

    // Subtract another vector from this vector.
    public Vector minus(Vector that) {
        if (this.coordinates.length != that.coordinates.length){
            throw new IllegalArgumentException("Dimensions disagree.");
        }
        double[] result = new double[this.coordinates.length];
        for (int i = 0; i < this.coordinates.length; i++) {
            result[i] = this.coordinates[i] - that.coordinates[i];
        }
        return new Vector(result);
    }

    // return the Euclidean distance between this and that
    public double distanceTo(Vector that) {
        if (this.coordinates.length != that.coordinates.length)
            throw new IllegalArgumentException("dimensions disagree");
        return this.minus(that).magnitude();
    }

    // Scale this vector by a scalar.
    public Vector scale(double alpha) {
        double[] result = new double[this.coordinates.length];
        for (int i = 0; i < this.coordinates.length; i++) {
            result[i] = this.coordinates[i] * alpha;
        }
        return new Vector(result);
    }

    // Compute the dot product of this vector with another vector.
    public double dot(Vector that) {
        double sum = 0.0;
        for (int i = 0; i < this.coordinates.length; i++) {
            sum += this.coordinates[i] * that.coordinates[i];
        }
        return sum;
    }

// The Euclidean norm (magnitude) of a vector can be computed
// using the dot product of the vector with itself. For a vector v
// with components v1, v2, ..., vn, the Euclidean norm |v| is
// calculated as the square root of the dot product of v with itself.
// This is derived from the definition of the dot product,
// where the dot product of a vector with itself is equivalent to
// the sum of the squares of its components. Taking the square root
// of this sum gives the Euclidean norm of the vector.

    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }
// Method to calculate the square of the magnitude of the vector
public double magnitudeSquared() {
    return this.dot(this);
}
    // Compute the direction (unit vector) of this vector.
    public Vector direction() {
        if (this.magnitude() == 0) throw new ArithmeticException("Zero-vector doesn't have a direction.");
        return this.scale(1 / this.magnitude());
    }

    // Get the i-th Cartesian coordinate of this vector.
    public double cartesian(int i) {
        return coordinates[i];
    }

    public int length(){
        return coordinates.length;
    }
    /*
     * When a class implements an interface in Java,
     *  it must provide implementations for all the methods declared in that interface.
     *  The Comparable interface declares a single method, so it must be Overridden
     */
    @Override
    public int compareTo(Vector other) {
        int minLength = Math.min(this.coordinates.length, other.coordinates.length);

        for (int i = 0; i < minLength; i++) {
            int comparison = Double.compare(this.coordinates[i], other.coordinates[i]);
            if (comparison != 0) {
                return comparison;
            }
        }

        // If we've reached this point, all compared elements were equal
        return Integer.compare(this.coordinates.length, other.coordinates.length);
    }
    // Add a toString() method to Vector that returns the vector components,
//  separated by commas, and enclosed in matching parentheses.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < coordinates.length; i++) {
            sb.append(coordinates[i]);
            if (i < coordinates.length - 1) sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }
    /*
     * Override the equals() and hashCode() methods for Vector (PROGRAM
3.3.3) so that two Vector objects are equal if they have the same length and the
corresponding coordinates are equal.
     */
    @Override
    public boolean equals(Object object){
        if (object == null) return false;
        if (this.getClass() != object.getClass()) return false;
        Vector that = (Vector) object;
        if (this.coordinates.length != that.coordinates.length){
            throw new IllegalArgumentException("Dimensions disagree.");
        }
        for(int i = 0; i < this.coordinates.length; i++){
            if(this.coordinates[i] != that.coordinates[i]){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for(int i = 0; i < this.coordinates.length; i++){
            result += 31 * (Objects.hash(this.coordinates[i]));
        }
        return result;
    }

    // Main method for testing
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{4.0, 5.0, 6.0});
        Vector vector2 = new Vector(new double[]{1.0, 2.0});

        double distance = vector2.minus(vector1).magnitude();

        StdOut.println("Distance: " + distance);

    }
}
