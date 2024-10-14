import lib.StdDraw;
/*
 * Write a program that simulates the output of an oscilloscope
and produces Lissajous patterns. These patterns are named after the French physi-
cist, Jules A. Lissajous, who studied the patterns that arise when two mutually per-
pendicular periodic disturbances occur simultaneously. Assume that the inputs are
sinusoidal, so that the following parametric equations describe the curve:
x(t ) = Ax sin (wxt + theta_x)
y(t ) = Ay sin (wyt + theta_y)
Take the six arguments Ax ,, wx ,, theta_x , Ay ,, wy , and theta_y from the command line.
 */
public class Oscilloscope {

    
        public static void main(String[] args) {
            // Parse command-line arguments
            double Ax = Double.parseDouble(args[0]);
            double wx = Double.parseDouble(args[1]);
            double phix = Double.parseDouble(args[2]);
            double Ay = Double.parseDouble(args[3]);
            double wy = Double.parseDouble(args[4]);
            double phiy = Double.parseDouble(args[5]);
    
            // Setup StdDraw
            StdDraw.setCanvasSize(800, 800);
            StdDraw.setXscale(-1.1 * Ax, 1.1 * Ax);
            StdDraw.setYscale(-1.1 * Ay, 1.1 * Ay);
            StdDraw.setPenRadius(0.005);
    
            // Draw the Lissajous pattern
            for (double t = 0.0; t <= 100; t += 0.01) {
                double x = Ax * Math.sin(wx * t + phix);
                double y = Ay * Math.sin(wy * t + phiy);
                StdDraw.point(x, y);
            }
        }
    }
     
