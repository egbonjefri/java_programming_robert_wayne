
import lib.StdOut;
import lib.In;
import lib.Stopwatch;



public class Foursum {
    public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    for( int l = k+1; l < n; l++){
                    if (a[i] + a[j] + a[k] + a[l] == 0) {
                        count++;
                    }
                }
                }
            }
        }
        return count;
    }

    public static void main(String[] args)  {
        In in = new In(args[0]);
        int[] a = in.readAllInts();

        Stopwatch timer = new Stopwatch();
        int count = count(a);
        StdOut.println("elapsed time = " + timer.elapsedTime());
        StdOut.println(count);
    }
}
/**
 * The order of growth (time complexity) of this program is O(n^4). 
 * This is determined by analyzing the nested loops within the `count` method.
 *
 * Explanation:
 * - The outermost loop iterates 'n' times.
 * - The second loop iterates approximately 'n - 1' times.
 * - The third loop iterates approximately 'n - 2' times.
 * - The innermost loop iterates approximately 'n - 3' times.
 *
 * Since each loop iterates roughly 'n' times, the total iterations of the innermost statement (the if condition) can be estimated as:
 *
 * ∑_{i=0}^{n-1} ∑_{j=i+1}^{n-1} ∑_{k=j+1}^{n-1} ∑_{l=k+1}^{n-1} 1
 *
 * This represents the number of ways to choose 4 distinct indices out of 'n' possible indices.
 * When you need to select a subset of elements from a set, where the order of selection doesn't matter, 
 * it’s a combination problem, calculated using the formula:
 *
 * (n choose 4) = n! / (4! * (n - 4)!) = n * (n - 1) * (n - 2) * (n - 3) / 4!(n-4)!
 *
 * This formula simplifies to O(n^4), indicating that the running time of the `count` method
 * grows proportionally to the fourth power of the input size 'n'.

 *
 * To estimate the largest input size that this program can handle in an hour:
 * 
 * 1. An hour has 3600 seconds.
 * 2. Assume each iteration of the innermost loop takes t microseconds (t * 10^-6 seconds).
 *
 * The total time taken by the program is approximately:
 * t * n(n-1)(n-2)(n-3) / 24
 *
 * Setting this equal to 3600 seconds and solving for n:
 *
 * t * n(n-1)(n-2)(n-3) / 24 <= 3600
 *
 * Assuming t = 1 microsecond (1 * 10^-6 seconds):
 *
 * n(n-1)(n-2)(n-3) / (24 * 10^6) <= 3600
 *
 * Multiplying both sides by 24 * 10^6:
 *
 * n(n-1)(n-2)(n-3) <= 86400 * 10^6
 *
 * Taking the fourth root of both sides to solve for n:
 *
 * n <= (86400 * 10^6)^(1/4)
 *
 * n <= 965
 *
 * Thus, the largest input size n that this program can handle in an hour is approximately 965.

 */
