/*
 * Write a recursive Turtle client that produces these recur-
sive patterns.
 */
import lib.Turtle;

public class GosperIsland {
    private static final double TURN_ANGLE = 60;   // Regular turn angle for Gosper curve formation
    private static final double SPECIAL_ANGLE = Math.acos(5 * Math.sqrt(7) / 14);   // Special angle calculation

    private Turtle turtle;
    private double size;

    public GosperIsland(int n) {
        initializeTurtle(n);
        drawGosperIsland(n);
    }

    private void initializeTurtle(int n) {
        double scaleFactor = 7.0 / 16.0;
        double decayFactor = Math.sqrt(7.0);
        size = scaleFactor / Math.pow(decayFactor, n);
        double initialX = scaleFactor * 2;  
        double initialY = scaleFactor / 4; 

        turtle = new Turtle(initialX, initialY, 0.0);
    }

    private void drawGosperIsland(int n) {
        for (int i = 0; i < 6; i++) {  // Draw 6 Gosper curves to form an island
            gosper(n);
            turtle.turnLeft(TURN_ANGLE);
        }
    }

    // Gosper curve of order n
    private void gosper(int n) {
        if (n == 0) {
            turtle.goForward(size);
        } else {
            turtle.turnLeft(-SPECIAL_ANGLE);
            gosper(n-1);
            turtle.turnLeft(TURN_ANGLE);
            gosper(n-1);
            turtle.turnLeft(-TURN_ANGLE);
            gosper(n-1);
            turtle.turnLeft(SPECIAL_ANGLE);
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide an order for the Gosper Island.");
            return;
        }
        try {
            int n = Integer.parseInt(args[0]);
            new GosperIsland(n);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    }
}
