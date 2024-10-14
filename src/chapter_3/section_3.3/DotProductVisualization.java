
import lib.StdDraw;
import lib.Vector;


public class DotProductVisualization {

    public static void main(String[] args) {
        StdDraw.setScale(-1.2, 1.2); // Set drawing scale for better view
        StdDraw.clear(StdDraw.LIGHT_GRAY); // Set a background color

        Vector u = new Vector(new double[] {1.0, 0.0}); // Vector along x-axis

        for (double angle = 0; angle <= 2 * Math.PI; angle += Math.PI / 36) { // Increments of 5 degrees
            StdDraw.clear(); // Clear previous drawing

            Vector v = new Vector(new double[] {Math.cos(angle), Math.sin(angle)});
            double dotProduct = u.dot(v);

            // Draw the unit circle (optional)
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.circle(0, 0, 1);

            // Draw vectors u and v
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.line(0, 0, u.cartesian(0), u.cartesian(1)); // Draw u
            StdDraw.line(0, 0, v.cartesian(0), v.cartesian(1)); // Draw v

            // Display angle and dot product
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text(0.5, 1.1, "Angle: " + Math.round(Math.toDegrees(angle)) + " | Dot Product: " + dotProduct);

            StdDraw.show();
            StdDraw.pause(500); // Add a short delay for visual effect        
        }
    }
}
