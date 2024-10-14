package lib;


public class Stopwatch {

    private long start;
    private Long stopTime = null; 

    /**
     * Initializes a new stopwatch.
     */
    public Stopwatch() {
        start = System.currentTimeMillis();
    }

    /**
     * Stops the stopwatch. This method records the time at which it was stopped, and
     * subsequent calls to elapsedTime will return the time from the start to this stop moment.
     */
    public void stop() {
        if (stopTime == null) { // Only record the stop time if the stopwatch hasn't been stopped before
            stopTime = System.currentTimeMillis();
        }
    }

    /**
     * Restarts the stopwatch. This resets the start time to the current moment and clears any stop time.
     */
    public void restart() {
        start = System.currentTimeMillis();
        stopTime = null; // Clear the stop time
    }

    /**
     * Returns the elapsed CPU time (in seconds) since the stopwatch was created, or until it was stopped.
     *
     * @return elapsed CPU time (in seconds) since the stopwatch was created or until it was stopped
     */
    public double elapsedTime() {
        long now;
        if (stopTime != null) {
            now = stopTime;
        } else {
            now = System.currentTimeMillis();
        }
        return (now - start) / 1000.0;
    }

    private static double harmonicLoop(int n){
        double sum = 0.0;
        for (int i = 1; i <= n; i++){
                sum += 1.0/i;
            }
        return sum;
    }

    private static double harmonicRecursive(int n){
        if (n <= 1) return 1.0;
        return 1.0 / n + harmonicRecursive(n - 1);
    }
    
    /**
     * Unit tests the {@code Stopwatch} data type.
     * Takes a command-line argument {@code n} and computes the
     * sum of the square roots of the first {@code n} positive integers,
     * first using {@code Math.sqrt()}, then using {@code Math.pow()}.
     * It prints to standard output the sum and the amount of time to
     * compute the sum. Note that the discrete sum can be approximated by
     * an integral - the sum should be approximately 2/3 * (n^(3/2) - 1).
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        // sum of square roots of integers from 1 to n using Math.sqrt(x).
        Stopwatch timer1 = new Stopwatch();
        double sum1 = 0.0;
        for (int i = 1; i <= n; i++) {
            sum1 += Math.sqrt(i);
        }
        double time1 = timer1.elapsedTime();
        StdOut.printf("%e (%.2f seconds)\n", sum1, time1);

        // sum of square roots of integers from 1 to n using Math.pow(x, 0.5).
        Stopwatch timer2 = new Stopwatch();
        double sum2 = 0.0;
        for (int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++){
                sum2 += Math.pow(i, j);
            }
        }
        double time2 = timer2.elapsedTime();
        StdOut.printf("%e (%.2f seconds)\n", sum2, time2);

        // nth harmonic number using for loop
        Stopwatch timer3 = new Stopwatch();
        double sum3 = harmonicLoop(n);
        double time3 = timer3.elapsedTime();
        StdOut.printf("%e (%.2f seconds)\n", sum3, time3);

        // nth harmonic number using recursion
        Stopwatch timer4 = new Stopwatch();
        double sum4 = harmonicRecursive(n);
        double time4 = timer4.elapsedTime();
        StdOut.printf("%e (%.2f seconds)\n", sum4, time4);
    }
}
