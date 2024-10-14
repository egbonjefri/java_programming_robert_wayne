
import lib.StdDraw;

import java.awt.Color;

public class Body {
    private double x, y;         // Position
    private final double radius; // Radius of orbit
    private final double omega;  // Angular speed 
    private double angle;        // Current angle
    private final Color color;   // Color for drawing
    private double objectRadius = 0.05;

    public Body(double x, double y, double radius, double omega, double angle, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.omega = omega;
        this.angle = angle; // Initial angle
        this.color = color;
    }

    public void move(double dt) {
        this.angle += omega; 
        updatePosition(); // Update x and y based on angle and radius 
    }

    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(x, y, objectRadius); 
    }

    private void updatePosition() {
        x = 0.5 + radius * Math.cos(angle); 
        y = 0.5 + radius * Math.sin(angle); 
    }

    public static void main(String[] args) {
        StdDraw.setCanvasSize(800, 800);
        StdDraw.enableDoubleBuffering();

    
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);

        Body body1 = new Body(0.5, 0.5, 0.2, 0.01, 0, Color.RED);
        Body body2 = new Body(0.5, 0.5, 0.3, 0.008, Math.PI / 2, Color.BLUE); 

        // Animation loop
        while (true) {
            StdDraw.clear();

            // Move the bodies
            body1.move(0.02); 
            body2.move(0.02); 

            // Draw the bodies
            body1.draw();
            body2.draw();

            StdDraw.show();
            StdDraw.pause(20);
        }
    }
}