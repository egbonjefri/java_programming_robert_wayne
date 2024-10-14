import lib.StdOut;
import lib.StdDraw;

public class RelativelyPrime {
    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("Usage: java RelativelyPrime <n>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int[][] grid = new int[n][n];
        visualizeGrid(grid);

    }
    public static void visualizeGrid(int[][] grid) {
        int n = grid.length; 
        StdDraw.setXscale(-1, n);
        StdDraw.setYscale(-1, n);
        StdDraw.clear(StdDraw.GRAY);
        StdDraw.enableDoubleBuffering();
    
        double squareSize = 0.45; // Define square size
        double padding = 0.05; // Define padding
    
        // Adjusted scale factors to account for padding
        double scaleFactorX = 1.0 / (1 + padding);
        double scaleFactorY = 1.0 / (1 + padding);
    
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double x = j * scaleFactorX + scaleFactorX / 2 - 0.5;
                double y = (n - i - 1) * scaleFactorY + scaleFactorY / 2 - 0.5;
    
                // Determine color based on the site's state
                if (isOddBinomialCoefficient(i,j)) {
                    StdDraw.setPenColor(StdDraw.BOOK_BLUE); 
                } else {
                    StdDraw.setPenColor(StdDraw.BLACK); 
                }
    
                StdDraw.filledSquare(x, y, squareSize / 2); // Draw the site
            }
        }
        StdDraw.show();
    }
    // Function to calculate the greatest common divisor (GCD) of two numbers
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
 
        return gcd(b, a % b);
    }

       // Method to check if the binomial coefficient is odd
       public static boolean isOddBinomialCoefficient(int n, int k) {
        // The binomial coefficient is odd if and only if there are no carries in the binary addition of n and k
        return (n & k) == k;
    }
}