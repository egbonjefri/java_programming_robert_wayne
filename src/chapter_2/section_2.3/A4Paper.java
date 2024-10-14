import lib.StdDraw;



public class A4Paper {
    
    public static void drawCut(int n, double x, double y, double width, double height) {
        if (n == 0) return;
        // Draw the current piece before cutting
        StdDraw.rectangle(x, y, width / 2, height / 2);

        if (n % 2 == 0) {
            // Vertical cut: Cut the paper into left and right pieces
            StdDraw.setPenColor(StdDraw.RED);
            drawCut(n - 1, x - width / 4, y, width / 2, height);
            drawCut(n - 1, x + width / 4, y, width / 2, height);
        } else {
            // Horizontal cut: Cut the paper into top and bottom pieces
            StdDraw.setPenColor(StdDraw.BLUE);
            drawCut(n - 1, x, y - height / 4, width, height / 2);
            drawCut(n - 1, x, y + height / 4, width, height / 2);
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]); // Take n as command-line argument

        // Initial dimensions for A0 paper
        double width = Math.sqrt(2);
        double height = 1;

        // Setup StdDraw canvas
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(-width, width);
        StdDraw.setYscale(-height, height);
        StdDraw.clear(StdDraw.LIGHT_GRAY);
        drawCut(n, 0, 0, width, height);
    }
}