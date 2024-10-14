import lib.StdDraw;
/*
 * Write a program Circles that draws ﬁlled circles of random radii at ran-
dom positions in the unit square, producing images like those below. Your program
should take four command-line arguments: the number of circles, the probability
that each circle is black, the minimum radius, and the maximum radius.
 */
public class Circles {
    public static void main(String[] args) {
        // Check if there are exactly four command-line arguments
        if (args.length != 4) {
            System.out.println("Usage: java Circles <numCircles> <blackProbability> <minRadius> <maxRadius>");
            return;
        }

        // Parse command-line arguments
        int numCircles = Integer.parseInt(args[0]);
        double blackProbability = Double.parseDouble(args[1]);
        double minRadius = Double.parseDouble(args[2]);
        double maxRadius = Double.parseDouble(args[3]);

        // Set up the canvas
        StdDraw.setCanvasSize(1920, 1600);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);

        // Draw filled circles with random radii and positions
        for (int i = 0; i < numCircles; i++) {
            //generates a random value for the variable radius within the specified range [minRadius, maxRadius)
            double radius = minRadius + Math.random() * (maxRadius - minRadius);
            double x = radius + Math.random() * (1 - 2 * radius);
            double y = radius + Math.random() * (1 - 2 * radius);

            // Determine whether the circle is black based on the probability
            if (Math.random()< blackProbability) {
                StdDraw.setPenColor(StdDraw.BLACK);
            } else {
                StdDraw.setPenColor(StdDraw.WHITE);
            }

            // Draw the filled circle
            StdDraw.filledCircle(x, y, radius);
        }
    }
}
