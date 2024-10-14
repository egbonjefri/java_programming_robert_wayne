
import lib.Stopwatch;
import lib.StdDraw;
import lib.StdOut;


public class FibonacciBad {
    public static int f(int n){
        if (n == 0) return 1;
        return f(n-1) + f(n-1);
    }
    public static void main(String[] args) {
    int maxN = 20;
    double[] times = new double[maxN + 1];

    for (int i = 0; i <= maxN; i++) {
        Stopwatch timer = new Stopwatch();
        f(i);
        double elapsedTime = timer.elapsedTime();
        times[i] = elapsedTime;
        StdOut.println("n=" + i + " elapsed time = " + elapsedTime);
    }

    // Setup StdDraw canvas
    StdDraw.setCanvasSize(800, 600);
    StdDraw.setXscale(-1.5, maxN + 1);
    StdDraw.setYscale(-0.001, times[maxN] * 1.1); // Scale a bit higher than max time for better visualization

        StdDraw.text(maxN / 2, -times[maxN] * 0.05, "n");
        StdDraw.text(-maxN * 0.05, times[maxN] / 2, "Elapsed Time (seconds)", 90);

        // Plot points and connect them with lines
        for (int i = 0; i <= maxN; i++) {
            
            if (i > 0) {
                StdDraw.setPenRadius(0.001);
                StdDraw.line(i - 1, times[i - 1], i, times[i]);
            }
            StdDraw.setPenRadius(0.01);
            StdDraw.point(i, times[i]);
}
    }
}
