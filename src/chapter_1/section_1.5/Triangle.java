import lib.StdDraw;


public class Triangle
{
public static void main(String[] args)
{
    StdDraw.setCanvasSize(1980, 1080);
    StdDraw.clear(StdDraw.BLACK); 
    StdDraw.setPenColor(StdDraw.RED);
    int n = 50;
    StdDraw.setXscale(-10, n+10);
    StdDraw.setYscale(-10, n+10);
    for (int i = 0; i <= n; i++)
    StdDraw.line(0, n-i, i, 0);

}
}