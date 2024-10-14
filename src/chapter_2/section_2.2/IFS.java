/*
 * This program applies one transformation to a point, 
 * plotting the new point, and then repeating this process many times. 
 * Each point's position is dependent on the previous point's position,
 *  creating a chain of dependent transformations.
 * While the choice of transformation at each step is random, 
 * the transformations themselves are deterministic and carefully designed. 
 * Each transformation mathematically alters the point's position in a specific way.
 *  This means that over many iterations, these transformations guide the points into 
 * complex and detailed shapes due to the nature of fractals...
 * Such transformations are called affine transformations and include scaling, rotation, translation, and shearing
 */

import lib.StdArrayIO;
import lib.StdRandom;
import lib.StdDraw;
import lib.Matrix; 


public class IFS {

    public static void main(String[] args) {
        int trials = Integer.parseInt(args[0]);
        double[] dist = StdArrayIO.readDouble1D();
        double[][] cx = StdArrayIO.readDouble2D();
        double[][] cy = StdArrayIO.readDouble2D();

        double x = 0.0, y = 0.0;
        for (int t = 0; t < trials; t++) {
            int r = StdRandom.discrete(dist);

            // Point vector representing (x, y, 1). The '1' allows for affine transformations including translation.
            double[] point = {x, y, 1.0};

            // Applying affine transformation using matrix multiplication.
            // The transformation matrix cx[r] or cy[r] is multiplied with the point vector.
            // Affine transformation formula: [a b c]   [x]   [a*x + b*y + c]
            //                                [d e f] * [y] = [d*x + e*y + f]
            //                                [g h i]   [1]   [g*x + h*y + i]
            double[] newX = Matrix.multiply(cx[r], new double[][]{{point[0]}, {point[1]}, {point[2]}});
            double[] newY = Matrix.multiply(cy[r], new double[][]{{point[0]}, {point[1]}, {point[2]}});

            x = newX[0];
            y = newY[0];

            StdDraw.point(x, y);
        }
    }
}


/*
 * Affine transformations in a 2D space can be represented
 *  using 3x3 matrices when applied to a 3D point vector, 
 * where the third dimension is set to 1. This allows for both 
 * linear transformations (like scaling and rotation) and translation
 *  (shifting the position).
 */

 // Barnsley Fern in Java
// 1. Start with an initial point (x0, y0), usually (0, 0)
// 2. Randomly choose a transformation and apply it to the point
// 3. Plot the new point
// 4. Repeat steps 2-3 for a large number of iterations

// The transformations are:
// f1: x1 = 0, y1 = 0.16*y0
// f2: x1 = 0.85*x0 + 0.04*y0, y1 = -0.04*x0 + 0.85*y0 + 1.6
// f3: x1 = 0.2*x0 - 0.26*y0, y1 = 0.23*x0 + 0.22*y0 + 1.6
// f4: x1 = -0.15*x0 + 0.28*y0, y1 = 0.26*x0 + 0.24*y0 + 0.44

// Choose transformation f1 1% of the time, f2 85% of the time, f3 7% of the time, and f4 7% of the time.
// Plot points to create the fern
