import java.awt.Color;
import java.util.LinkedList;
import lib.StdDraw;
public class BouncingBall {
    public static void main(String[] args) {
        // Simulate the motion of a bouncing ball.
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.enableDoubleBuffering();

        double rx = 0.480, ry = 0.860;
        double vx = 0.015, vy = 0.023;
        double radius = 0.05;

        // List to store previous positions
        LinkedList<double[]> trail = new LinkedList<>();
        int trailLength = 50;
        while (true) {
            // Update ball position
            if (Math.abs(rx + vx) + radius > 1.0) vx = -vx;
            if (Math.abs(ry + vy) + radius > 1.0) vy = -vy;
            rx += vx;
            ry += vy;

            // Add current position to trail
            trail.addFirst(new double[]{rx, ry});
            if (trail.size() > trailLength) {
                trail.removeLast();
            }

            // Clear the background
            StdDraw.clear(StdDraw.LIGHT_GRAY);

            // Draw the trail
            for (int i = 0; i < trail.size(); i++) {
                double intensity = (double) (trail.size() - i) / trail.size();
                StdDraw.setPenColor(new Color(0, 0, 0, (float) intensity));
                double[] pos = trail.get(i);
                StdDraw.filledCircle(pos[0], pos[1], radius);
            }

            // Draw the ball
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledCircle(rx, ry, radius);

            // Display and pause
            StdDraw.show();
            StdDraw.pause(20);
        }
    }
}
