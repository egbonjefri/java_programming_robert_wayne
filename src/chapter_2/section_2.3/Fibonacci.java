public class Fibonacci {
    private static int n;
    private static long[] f = new long[n];
    public static long fib(int n) {
        
        if (n <= 1) return n;
        if(f[n] > 0) return f[n];
       f[n] = fib(n-1) + fib(n-2);
       return f[n];
    }
    public static long fibBad(int n) {
        if (n <= 1) return n;
        else return fib(n-1) + fib(n-2);
    }

    public static void main(String[] args) {
        n = Integer.parseInt(args[0]);
        // print results                                                     
        System.out.println(fib(n));
    }

}
/**
 * Computes the nth Fibonacci number recursively.
 * 
 * This implementation demonstrates that for fibonacci(n),
 *  the total number of recursive base case calls (to fib(0) and fib(1)) is exactly F(n+1), 
 * where F(n) is the nth Fibonacci number:
 * 
 * - Base Case Analysis:
 *   - fib(0) directly returns 0, constituting one base case call.
 *   - fib(1) directly returns 1, also constituting one base case call.
 *   Both fib(0) and fib(1) are essential base cases for the recursion, each counting as a single call when reached.
 * 
 * - Inductive Proof:
 *   - Assume fib(k) involves F(k+1) base case calls for some k >= 1.
 *   - To compute fib(k+1), the function calls fib(k) and fib(k-1), which, by induction, require F(k+1) and F(k) base case calls respectively.
 *   - Thus, fib(k+1) involves F(k+1) + F(k) = F(k+2) base case calls, aligning with the Fibonacci sequence definition.
 * 
 * - Conclusion:
 *   By induction, computing fibonacci(n) results in F(n+1) total calls to the base cases (fib(0) and fib(1)), reflecting the recursive exploration of every path down to these base cases. This highlights the method's exponential call growth and inefficiency for large n due to the redundancy of recalculating values.
 * 
 * @param n The index of the Fibonacci number to compute.
 * @return The nth Fibonacci number.
 */
