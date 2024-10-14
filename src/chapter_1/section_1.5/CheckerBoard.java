import lib.StdDraw;
/*
 * Write a program that takes an integer command-line argument n and plots
an n-by-n checkerboard with red and black squares. Color the lower-left square red.
 */
public class CheckerBoard {
    public static void main(String[] args) {
        // Check if a command-line argument is provided
        if (args.length != 1) {
            System.out.println("Usage: java Checkerboard <n>");
            System.exit(1);
        }

        // Parse the command-line argument as an integer
        int n = Integer.parseInt(args[0]);

        // Set canvas size
        StdDraw.setCanvasSize(1920, 1600);

        // Set the scale of the coordinate system
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(0, n);

        // Draw the checkerboard
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Determine the color of the square
                if ((i + j) % 2 == 0) {
                    StdDraw.setPenColor(StdDraw.RED);
                } else {
                    StdDraw.setPenColor(StdDraw.BLACK);
                }

                // Draw the square
                StdDraw.filledSquare(i + 0.5, j + 0.5, 0.5);
            }
        }
    }
}
