import lib.StdDraw;
import lib.StdIn;


public class PageRankVisualization {
    public static void main(String[] args) {
        // Assuming the first two inputs are the canvas width and height
        int canvasWidth = StdIn.readInt();
        int canvasHeight = StdIn.readInt();
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        // Set the x and y scales based on the range of your coordinates
        StdDraw.setXscale(0, 400);
        StdDraw.setYscale(0, 400);
        
        StdDraw.enableDoubleBuffering();
        
        // Assuming a known number of pages, if not, this could be an input too
        int numPages = StdIn.readInt();

        for (int i = 0; i < numPages; i++) {
            // Read each page's information from StdIn
            double pageRank = StdIn.readDouble(); // PageRank determines the size
            double x = StdIn.readDouble();        // x-coordinate
            double y = StdIn.readDouble();        // y-coordinate

            // Normalize PageRank for drawing (you might need to adjust this)
            double dotSize = pageRank * 75; 
            StdDraw.setPenColor(StdDraw.BLACK); // Set the color for the circle
            // Draw the dot representing the page
            StdDraw.filledCircle(x, y, dotSize);

            StdDraw.setPenColor(StdDraw.WHITE); // Set the color for the text
            StdDraw.text(x, y, String.valueOf(i));
        }

        // Show the drawing
        StdDraw.show();
    }
}
