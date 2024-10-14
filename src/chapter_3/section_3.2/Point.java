/*
 * Write a data type Point that implements the following API:
    public class Point
        Point(double x, double y)
double      distanceTo(Point q)         Euclidean distance between this point and q
String      toString()                  string representation
 */
import lib.StdOut;

public class Point {

    private double x0;
    private double y0;

    public Point(double x, double y){
        this.x0 = x;
        this.y0 = y;
    }

    // Computes Euclidean distance between this point and another point q
    // Distance formula: d = sqrt((x2 - x1)^2 + (y2 - y1)^2)
    public double distanceTo(Point q){
        double xDiff = q.x0 - this.x0;
        double yDiff = q.y0 - this.y0;
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    // Provides a string representation of the point
    public String toString(){
        return "(" + x0 + ", " + y0 + ")";
    }

    // Main method to test the Point class functionality
    public static void main(String[] args) {
        // Create two points
        Point p1 = new Point(3, 4);
        Point p2 = new Point(6, 8);

        // Calculate and print the distance between them
        StdOut.println("The distance between " + p1 + " and " + p2 + " is: " + p1.distanceTo(p2));

        // Demonstrate the toString method
        StdOut.println("Point 1: " + p1);
        StdOut.println("Point 2: " + p2);
    }
}
