import lib.StdDraw;
import lib.StdIn;

public class PlotFiller {
    public static void main(String[] args)
{
    StdDraw.setCanvasSize(1980, 1600);
    StdDraw.clear(StdDraw.BLACK); 
    StdDraw.setPenColor(StdDraw.RED);
// Scale as per first four values.
    double x0 = StdIn.readDouble();
    double y0 = StdIn.readDouble();
    double x1 = StdIn.readDouble();
    double y1 = StdIn.readDouble();
    StdDraw.setXscale(x0, x1);
    StdDraw.setYscale(y0, y1);
// Read the points and plot to standard drawing.
    StdDraw.enableDoubleBuffering();
    while (!StdIn.isEmpty())
    {
    double x = StdIn.readDouble();
    double y = StdIn.readDouble();
    StdDraw.point(x, y);
}
    StdDraw.show();

}
}
