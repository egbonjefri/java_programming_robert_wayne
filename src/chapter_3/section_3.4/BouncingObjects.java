
import lib.StdDraw;
import lib.StdRandom;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BouncingObjects {
    
    private double rx, ry;
    private double vx, vy;
    private double radius;
    private LinkedList<double[]> trail;
    private int trailLength;
    private double[] buffer; // Buffer to hold the current x, y position

    public BouncingObjects(){
        // Simulate the motion of a bouncing ball.
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.enableDoubleBuffering();
        this.rx = StdRandom.uniformDouble();
        this.ry = StdRandom.uniformDouble();
        this.vx = StdRandom.uniformDouble() / 10;
        this.vy = StdRandom.uniformDouble() / 10;
        this.radius = 0.05;
        this.trailLength = 50;
        this.trail = new LinkedList<>();
        this.buffer = new double[]{rx, ry}; // Initialize buffer
}

public void updatePosition() { // Renamed for clarity 
    if (Math.abs(rx + vx) + this.radius > 1.0) vx = -vx;
    if (Math.abs(ry + vy) + this.radius > 1.0) vy = -vy;
    rx += vx;
    ry += vy;

    // Update the buffer
    this.buffer[0] = rx;
    this.buffer[1] = ry;
}

public double[] getDrawingBuffer() {
    return buffer;
}

    public void drawBalls(){
        while (true) {
            // Update ball position
            if (Math.abs(rx + vx) + this.radius > 1.0) vx = -vx;
            if (Math.abs(ry + vy) + this.radius > 1.0) vy = -vy;
            rx += vx;
            ry += vy;

            // Add current position to trail
            this.trail.addFirst(new double[]{rx, ry});
            if (this.trail.size() > this.trailLength) {
                this.trail.removeLast();
            }

            // Clear the background
            StdDraw.clear(StdDraw.LIGHT_GRAY);

            // Draw the trail
            for (int i = 0; i < this.trail.size(); i++) {
                double intensity = (double) (this.trail.size() - i) / this.trail.size();
                StdDraw.setPenColor(new Color(0, 0, 0, (float) intensity));
                double[] pos = this.trail.get(i);
                StdDraw.filledCircle(pos[0], pos[1], this.radius);
            }

            // Draw the ball
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledCircle(rx, ry, this.radius);

            // Display and pause
            StdDraw.show();
            StdDraw.pause(20);
        }
        
    }
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        // Create a list to hold the objects
        List<BouncingObjects> objects = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            objects.add(new BouncingObjects());
        }

        // Create the threads and start them
        List<Thread> threads = new ArrayList<>();
        for (BouncingObjects obj : objects) {
            Thread t = new Thread(() -> obj.drawBalls());
            threads.add(t);
            t.start();
        }

        // Wait for all threads to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
