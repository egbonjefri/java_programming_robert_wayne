


public class Recursive{
    /**
 * The factorial function is defined only for non-negative integers.
 * It follows the recurrence relation: n! = n * (n-1)! for n >= 1, with 0! = 1 as the base case.
 * 
 * Attempting to apply this recurrence relation to negative integers leads to problems:
 * - There is no natural base case for negative integers, leading to an infinite recursive descent.
 * - If we naively extend the relation to n < 0, we encounter a division by zero. 
 *   For example, trying to solve 0! = 0 * (-1)! leads to dividing by zero to calculate (-1)!.
 * 
 * Thus, the factorial function is not defined for negative integers. For generalization beyond 
 * positive integers, mathematicians use the Gamma function, which is defined for non-integer and 
 * certain negative values but with different properties and constraints.
 */

    public static long factorial(int n){
        if (n == 1) return 1;
        return n * factorial(n-1);
    }


    /**
 * The function lnFactorial(int n) calculates the natural logarithm of the factorial of n (ln(n!)).
 * 
 * This is based on two key concepts:
 * 1. Factorial Definition: n! is the product of all positive integers up to n.
 * 2. Logarithm of a Product: The natural logarithm of a product is equal to the sum of the logarithms of its factors.
 * ln(a×b)=ln(a)+ln(b)
 * Therefore, ln(n!) can be expressed as the sum ln(n) + ln(n-1) + ... + ln(1), 
 * applying the logarithm property to the factorial definition.
 */
    public static double lnFactorial(int n) {
        if (n == 0 || n == 1) {
            return 0; // ln(1) is 0
        } else {
            return Math.log(n) + lnFactorial(n - 1);
        }
    }
    public static String convert(int n){
        if(n == 1) return "1";
        return convert(n/2) + n%2;
    }
    public static int gcd(int p, int q){
        if (q == 0) return p;
        return gcd(q, p % q);
    }
    public static double harmonic(int n){
        if (n == 1) return 1.0;
        return harmonic(n-1) + 1.0/n;
    }
    public static int mystery(int a, int b){
        if (b == 0) return 1;
        if (b % 2 == 0) return mystery(a*a, b/2);
        return mystery(a*a, b/2) * a;
}
public static int mcCarthy(int n) {
    if (n > 100) return n - 10;
    else return mcCarthy(mcCarthy(n+11));
 }
    public static void main(String[] args){
        System.out.println(mcCarthy(110));
    }
}
/**
 * Analysis of the Euclidean algorithm's efficiency for computing gcd(p, q).
 * 
 * Part 1: Decrease by at Least a Factor of 2 for Every Second Recursive Call
 * - The Euclidean algorithm is defined as gcd(p, q) = gcd(q, p % q), with the base case being gcd(p, 0) = p.
 * - Proof: In every pair of recursive calls (gcd(p, q) and then gcd(q, p % q)), 
 * the second argument decreases by at least a factor of 2 at least once. 
 * This is because if q <= p/2, the second argument is at most half of p. 
 * If q > p/2, then p % q < p/2, ensuring a significant decrease every second call.
 * 
 * Part 2: At Most 2 log2(n) + 1 Recursive Calls
 * - Given the decrease by at least half every second call, the maximum recursion depth before 
 * reaching the base case can be approximated by doubling the number of times n can be halved (2 log2(n)),
 *  plus 1 for the final call to reach the base case.
 * - Conclusion: gcd(p, q) requires at most 2 log2(n) + 1 recursive calls, 
 * where n is the larger of p and q, showcasing the logarithmic efficiency of the algorithm.
 * 
 * This analysis highlights the Euclidean algorithm's effectiveness and efficiency, 
 * demonstrating its logarithmic time complexity in terms of recursive calls.
 */


 /*
The McCarthy 91 function is a recursive function defined as follows:
- If n > 100, the function returns n - 10.
- For n <= 100, the function recursively calls itself with n + 11.

For the input mcCarthy(50):
- The function repeatedly increases the input by 11 and recursively calls itself,
  until n exceeds 100, at which point it starts decreasing by 10 with each recursive return.
- This process ensures that for any initial n <= 100, the function eventually returns 91.
- For n = 50, after several recursive calls, the function first reaches a base case with n > 100,
  then through a series of returns, eventually returns 91.

The exact number of recursive calls for mcCarthy(50) involves a complex series of recursions
due to the function's double recursive calls. However, the key takeaway is that for any n <= 100,
the function will ultimately return 91, and for n > 100, it returns n - 10.

Proving the base case is reached for all positive integers:
1. If n > 100, the function directly returns n - 10, avoiding infinite recursion.
2. For n <= 100, the function ensures an eventual return by increasing n beyond 100
   (requiring a finite number of steps), where it then begins to decrease, ensuring
   that a base case (n > 100) is eventually met. Therefore, the function does not enter
   an infinite loop for any positive integer n, always reaching a terminating condition.

Thus, the McCarthy 91 function demonstrates interesting behavior with recursive functions,
guaranteeing termination and a consistent return value of 91 for inputs n <= 100.
*/
