import lib.StdDraw;
/*
 * Write a program that takes an integer command-line argument n and plots
a rose with n petals (if n is odd) or 2n petals (if n is even), by plotting the polar
coordinates (r, theta) of the function r = sin(n * theta) for theta ranging from 0 to 2pi radians.
 */
public class RosePlot {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java RosePlot <number_of_petals>");
            return;
        }

        int n = Integer.parseInt(args[0]);

        int canvasSize = 800;
        StdDraw.setCanvasSize(canvasSize, canvasSize);
        StdDraw.setXscale(-1.5, 1.5);
        StdDraw.setYscale(-1.5, 1.5);
        if(n%2==0){
        plotRose(2*n);
        }
        else{
        plotRose(n);
        }
       
    }

    private static void plotRose(int n) {
        int numPoints = 1000;
        double deltaTheta = 2 * Math.PI / numPoints;

        for (double theta = 0; theta <= 2 * Math.PI; theta += deltaTheta) {
            double r = Math.sin(n * theta);
            double x = r * Math.cos(theta);
            double y = r * Math.sin(theta);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.02);
            StdDraw.point(x, y);
        }
    }
}
