import lib.StdDraw;
public class SinGraph{
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        double[] x = new double[n+1];
        double[] y = new double[n+1];
        StdDraw.setCanvasSize(1980, 1600);
        StdDraw.clear(StdDraw.BLACK); 
        StdDraw.setPenColor(StdDraw.RED);
        for (int i = 0; i <= n; i++){
            x[i] = Math.PI * i / n;
        }
        for (int i = 0; i <= n; i++) {
        y[i] = Math.sin(4*x[i]) + Math.sin(20*x[i]);
        }
        StdDraw.setXscale(0,Math.PI);
        StdDraw.setYscale(-2.0, 2.0);
        for (int i = 1; i <= n; i++){
        StdDraw.line(x[i-1], y[i-1], x[i], y[i]);
        }
    }
}
/*
 * Plotting the function by connecting successive points with lines
*   produces what is known as a
* piecewise linear approximation
 */