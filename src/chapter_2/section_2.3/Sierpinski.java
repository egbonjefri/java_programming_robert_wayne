
/*
 * Write a recursive program to draw Sierpin-
ski triangles (see PROGRAM 2.2.3). As with Htree, use a command-line
argument to control the depth of the recursion.
 */
import lib.StdDraw;
import lib.StdRandom;

public class Sierpinski {
    public static void main(String[] args){
        StdDraw.setCanvasSize(1980, 1600);
        StdDraw.clear(StdDraw.BLACK); 
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(-0.2, 0.9);
        int trials = Integer.parseInt(args[0]);
        double[] cx = { 0.000, 1.000, 0.500 };
        double[] cy = { 0.000, 0.000, 0.866 };
        double x = 0.0, y = 0.0;
        StdDraw.line(cx[0], cy[0], cx[2], cy[2]);
        StdDraw.line(cx[2], cy[2], cx[1], cy[1]);
        StdDraw.line(cx[1], cy[1], cx[0], cy[0]);
        
        for (int t = 0; t < trials; t++){
        int r = StdRandom.uniformInt(3);
        x = (x + cx[r]) / 2.0;
        y = (y + cy[r]) / 2.0;
         StdDraw.setPenColor(StdDraw.WHITE);
         StdDraw.setPenRadius(0.025);
        StdDraw.point(x, y);
}
    }
}
