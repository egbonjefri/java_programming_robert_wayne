import lib.StdDraw;

/*
 * Write a program that takes three double command-line ar-
guments R, r, and a and draws the resulting spirograph. A spirograph (technically,
an epicycloid) is a curve formed by rolling a circle of radius r around a larger ﬁxed
circle of radius R. If the pen offset from the center of the rolling circle is (r + a), then
the equation of the resulting curve at time t is given by
x(t ) = (R + r ) cos (t ) - (r + a ) cos ((R + r )t /r)
y(t ) = (R + r ) sin (t ) - (r + a ) sin ((R + r )t /r)
Such curves were popularized by a best-selling toy that contains discs with gear
teeth on the edges and small holes that you could put a pen in to trace spirographs.
 */
public class Spirograph {

    public static void main(String[] args) {
        // Read command-line arguments
        double R = Double.parseDouble(args[0]); // Radius of the fixed circle
        double r = Double.parseDouble(args[1]); // Radius of the rolling circle
        double a = Double.parseDouble(args[2]); // Pen offset from the center of the rolling circle

        // Set up drawing canvas
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(-R - r - a, R + r + a);
        StdDraw.setYscale(-R - r - a, R + r + a);
        StdDraw.setPenRadius(0.005);

        // Draw the spirograph
        for (double t = 0.0; t <= 360.0; t += 0.1) {
            double tRadians = Math.toRadians(t);
            double x = (R + r) * Math.cos(tRadians) - (r + a) * Math.cos(((R + r) / r) * tRadians);
            double y = (R + r) * Math.sin(tRadians) - (r + a) * Math.sin(((R + r) / r) * tRadians);
            StdDraw.point(x, y);
        }
    }
}
