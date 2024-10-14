import lib.StdDraw;
/*
 * Write code to draw hearts, spades, clubs, and diamonds. To draw a heart,
draw a ﬁlled diamond, then attach two ﬁlled semicircles to the upper left and upper
right sides.
 */
public class CardShapes {
    public static void main(String[] args) {
        int canvasSize = 400;
        StdDraw.setCanvasSize(canvasSize, canvasSize);
        StdDraw.setXscale(0, canvasSize);
        StdDraw.setYscale(0, canvasSize);

        drawHeart(50, 50);
        drawSpade(150, 80);
        drawClub(250, 50);
        drawDiamond(350, 50);
    }
    private static void drawHeart(double x, double y) {
        double size = 20;

        // Draw half-filled diamond
        double[] diamondX = {x - size, x, x + size};
        double[] diamondY = {y + size, y, y + size};
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledPolygon(diamondX, diamondY);

        // Draw and fill semicircles manually
        double semiCircleRadius = size / 2;

        // Upper left semicircle
        drawAndFillSemicircle(x - size / 2, y + size, semiCircleRadius, true);

        // Upper right semicircle
        drawAndFillSemicircle(x + size / 2, y + size, semiCircleRadius, true);
    }
    private static void drawSpade(double x, double y) {
        double size = 20;

        double[] spadeX = {x + size, x, x - size};
        double[] spadeY = {y - size, y, y - size};
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledPolygon(spadeX, spadeY);
        drawAndFillSemicircle(x - 20 / 2, y - 20, 10, false);
        drawAndFillSemicircle(x + 20 / 2, y - 20, 10, false);
        // Draw and fill stem below the semicircles
        double stemWidth = 5;
        double stemHeight = 15;
        StdDraw.filledRectangle(x, y - size - stemHeight / 2, stemWidth, stemHeight);
    }

    private static void drawAndFillSemicircle(double x, double y, double radius, boolean upperHalf) {
        int numSegments = 100;

        double angleIncrement = Math.PI / numSegments;

        // Draw and fill the semicircle manually using a loop
        for (int i = 1; i <= numSegments; i++) {
            double angle = angleIncrement * i;
            double x1 = x + radius * Math.cos(angle);
            double y1 = upperHalf ? (y + radius * Math.sin(angle)) : (y - radius * Math.sin(angle));

            double x2 = x + radius * Math.cos(angle + angleIncrement);
            double y2 = upperHalf ? (y + radius * Math.sin(angle + angleIncrement)) : (y - radius * Math.sin(angle + angleIncrement));
            StdDraw.line(x, y, x1, y1);
            StdDraw.line(x, y, x2, y2);
        }
    }
   
  
        private static void drawClub(double x, double y) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(x, y + 15, 10);
        StdDraw.filledCircle(x - 8, y, 10);
        StdDraw.filledCircle(x + 8, y, 10);
              // Draw and fill stem below the semicircles
        double stemWidth = 5;
        double stemHeight = 5;
        StdDraw.filledRectangle(x, y - 10 - stemHeight / 2, stemWidth, stemHeight);
    }



    private static void drawDiamond(double x, double y) {
        double[] diamondX = {x, x - 10, x, x + 10};
        double[] diamondY = {y, y + 10, y + 20, y + 10};
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledPolygon(diamondX, diamondY);
    }
}
