import lib.StdRandom;
import lib.StdOut;
import lib.StdDraw;
import lib.Stopwatch;

public class CouponCollector {

    public static int getCoupon(int n) {
        return (int) (StdRandom.uniformDouble() * n);
    }

    public static int collectCoupons(int n) {
        boolean[] isCollected = new boolean[n];
        int count = 0, distinct = 0;
        while (distinct < n) {
            int r = getCoupon(n);
            count++;
            if (!isCollected[r]) {
                distinct++;
            }
            isCollected[r] = true;
        }
        return count;
    }

    public static void draw(int[] sizes, double[] times){
                // Set up the canvas
                StdDraw.setCanvasSize(800, 600);
                StdDraw.setXscale(0, sizes[sizes.length - 1] * 1.1);
                StdDraw.setYscale(0, times[times.length - 1] * 1.1);
                StdDraw.setPenRadius(0.005);
        
                // Plot the results
                for (int i = 0; i < sizes.length; i++) {
                    StdDraw.point(sizes[i], times[i]);
                    if (i > 0) {
                        StdDraw.line(sizes[i - 1], times[i - 1], sizes[i], times[i]);
                    }
                }
                StdDraw.text(sizes[sizes.length - 1] / 2, times[times.length - 1] * 1.05, "Coupon Collector Running Time");
                StdDraw.text(sizes[sizes.length - 1] / 2, times[times.length - 1] * 0.05, "Input Size (n)");
                StdDraw.text(sizes[sizes.length - 1] * 0.05, times[times.length - 1] / 2, "Time (ms)", 90);
    
    }
    public static void main(String[] args) {
        int[] sizes = {1000, 100000, 1000 * 1000 * 1000 * 1000};  // n, n^2, n^4 (calculated)
        double[] times = new double[sizes.length];

        for (int i = 0; i < sizes.length; i++) {
            int n = sizes[i];
            Stopwatch timer = new Stopwatch();
            collectCoupons(n);
            double duration = timer.elapsedTime();
            times[i] = duration;
            StdOut.println("n = " + n + ", Time: " + duration + " ms");
        }
        draw(sizes, times);

    }
}