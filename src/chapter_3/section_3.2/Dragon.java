
import lib.StdOut;
import lib.Turtle;
/*
 * Write a recursive Turtle client Dragon that draws dragon
curves
 */

public class Dragon {
    private Turtle turtle;

    public Dragon(int n) {
        int maxExtent = calculateMaxExtent(n);
        double x = 0.0;  
        double y = 0.0;  
        turtle = new Turtle(x, y, 0.0);
        turtle.setXscale(x - maxExtent, x + maxExtent);
        turtle.setYscale(y - maxExtent, y + maxExtent);
        drawDragonCurve(n, true);
    }

    private int calculateMaxExtent(int n) {
        return (int) Math.pow(2, n / 2);  // A rough approximation to handle drawing space dynamically
    }

    private void drawDragonCurve(int n, boolean original) {
        double distance = 1.0;
        int angle = original ? 90 : -90;
        if (n == 0) {
            turtle.goForward(distance);
        } else {
            drawDragonCurve(n-1, true);
            turtle.turnLeft(angle);
            drawDragonCurve(n-1, false);
        }
    }

    public static void main(String[] args) {
        try {
            int n = Integer.parseInt(args[0]);
            if (n < 0 || n > 15) {
                StdOut.println("Try a number between 0 and 15 next time.");
                return;
            }
            new Dragon(n);
        } catch (NumberFormatException e) {
            StdOut.println("Invalid input. Please enter a valid integer.");
        }
    }
}
