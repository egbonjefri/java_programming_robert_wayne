import lib.StdOut;
import lib.StdRandom;

public class SpherePointGenerator {
    public static void main(String[] args) {

        double a, b, c;
        do {
            // Generate random points in 3D space
            double x = 2 * StdRandom.uniformDouble() - 1; // Random value between -1 and 1
            double y = 2 * StdRandom.uniformDouble() - 1; // Random value between -1 and 1
            double z = 2 * StdRandom.uniformDouble() - 1; // Random value between -1 and 1

            double distanceSquared = x * x + y * y + z * z;

            // Check if the point is inside the unit sphere (within radius 1)
            if (distanceSquared <= 1.0) {
                double scale = 1.0 / Math.sqrt(distanceSquared);
                a = x * scale;
                b = y * scale;
                c = z * scale;
                break;
            }
        } while (true);

        StdOut.println("Random Point on the Sphere:");
        StdOut.println("a = " + a);
        StdOut.println("b = " + b);
        StdOut.println("c = " + c);
    }
}

/*
 * Generate Random 3D Points:

The method starts by generating random 3D points in a cube centered at the origin. 
These points are generated within the range [-1, 1] for each coordinate (x, y, z), 
effectively covering a cube.

Calculate the Distance from the Origin:
For each generated 3D point, the method calculates the Euclidean distance (squared) 
from the origin (0, 0, 0) using the Pythagorean theorem: distanceSquared = x^2 + y^2 + z^2.
Check if the Point is Inside the Sphere:

The generated points are inside the unit sphere if the squared distance is less than 
or equal to 1.0. In other words, if distanceSquared <= 1.0, the point is inside the sphere. 
This check ensures that the points generated are within the sphere with a radius of 1.
Scale the Point to the Sphere:

If a generated point is inside the unit sphere, it needs to be scaled to the surface of the sphere. 
To do this, the method calculates a scaling factor, which is the reciprocal of the square root of 
distanceSquared. This scaling factor ensures that the point moves from inside the sphere to the 
sphere's surface.
The scaled coordinates (a, b, c) of the point on the sphere are obtained as follows:
a = x * scale
b = y * scale
c = z * scale
Repeat Until a Valid Point is Found:

If the generated point is not inside the sphere (i.e., distanceSquared is greater than 1.0), 
the method continues generating random points until it finds one that is inside the sphere.
Output the Random Point:

Once a valid point on the sphere is found, the program outputs the coordinates (a, b, c) of 
that point.
 */