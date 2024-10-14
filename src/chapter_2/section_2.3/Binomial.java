/**
 * This Java function calculates the binomial coefficient, represented by C(n, k) or binom(n, k),
 * using a recursive approach. The binomial coefficient is crucial in combinatorics, representing
 * the number of ways to choose k items out of n items without considering the order.
 * 
 * The recursive formula used is:
 * C(n, k) = C(n-1, k) + C(n-1, k-1)
 * 
 * Explanation of the function:
 * 
 * Base Cases:
 * - If n and k are both 0 (C(0, 0)), the function returns 1.0, since there is exactly one way
 *   to choose zero elements from zero elements.
 * - If n < 0 or k < 0, the function returns 0.0. This condition handles scenarios where the
 *   concept of choosing k items from n items doesn't apply due to negative values.
 * 
 * Recursive Calculation:
 * - For valid n and k values, the function calculates C(n, k) by making two recursive calls:
 *   - C(n-1, k) calculates the number of combinations excluding a particular item from the
 *     set of n items, effectively choosing k items from n-1 items.
 *   - C(n-1, k-1) calculates the number of combinations including a particular item, thus
 *     choosing the remaining k-1 items from n-1 items.
 * - These recursive calls continue until reaching the base cases, building up the solution
 *   by combining the results of these smaller subproblems.
 * 
 * Application in Binomial Expansion:
 * - In the binomial expansion of (a + b)^n, the coefficients of the terms can be calculated
 *   using C(n, k), where k ranges from 0 to n. This function finds those coefficients by
 *   determining the number of combinations for each term, which corresponds to C(n, k).
 * 
 * Efficiency Note:
 * - While this recursive method directly implements the mathematical definition of the
 *   binomial coefficient, it's not efficient for larger n and k due to redundant calculations
 *   of the same values. A more efficient approach would use dynamic programming techniques,
 *   such as memoization or a bottom-up approach, to store previously computed values and
 *   reuse them, thereby avoiding unnecessary recalculations.
 */


import lib.StdArrayIO;

public class Binomial {
    // Function to calculate the binomial coefficient using recursion
    public static double binomial(int n, int k){
        if ((n == 0) && (k == 0)) return 1.0;
        if ((n < 0) || (k < 0)) return 0.0;
    return (binomial(n-1, k) + binomial(n-1, k-1))/2.0;
}
    // Function to calculate the binomial coefficient using dynamic programming
    public static double[][] binomialDP(int n, int k) {
        // Create a table to store previously calculated values
        double[][] dp = new double[n + 1][k + 1];
        
        // Base cases
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1.0;
        }
        for (int i = 1; i <= k; i++) {
            dp[0][i] = 0.0;
        }
        StdArrayIO.print(dp);
        // Fill the table in a bottom-up manner
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                double val = dp[i - 1][j] + dp[i - 1][j - 1];
                dp[i][j] = val;
            }
        }
        
        // The value of binomial coefficient C(n, k) is stored at dp[n][k]
        System.out.println("Binomial Coefficient of C(" + n + ", " + k + ") is: " + dp[n][k]);
        return dp;
    }
    
    public static void main(String[] args){
        StdArrayIO.print(binomialDP(10,5));
    }
}

/*
Comparison of Recursive and Dynamic Programming Approaches for Calculating Binomial Coefficients:

1. Efficiency:
   - Recursive Approach: It is less efficient due to the exponential number of calls for larger values of n and k. The approach performs many redundant calculations, as it does not store previously computed results, leading to a significant increase in computation time for larger inputs.
   - Dynamic Programming Approach: Significantly more efficient as it avoids redundant calculations by storing previously computed results in a table. This memoization ensures that each unique subproblem is solved only once, leading to polynomial time complexity, which is a substantial improvement over the recursive approach.

2. Space Complexity:
   - Recursive Approach: Has a lower space complexity since it only requires the stack space for recursive calls. However, for very deep recursion, this could lead to stack overflow issues.
   - Dynamic Programming Approach: Requires additional space for storing intermediate results in a table. The space complexity is O(n*k), where n is the first argument and k is the second argument of the binomial coefficient. This increased space requirement is the trade-off for the significant gain in time efficiency.

3. Understandability and Simplicity:
   - Recursive Approach: Conceptually simpler and more straightforward to understand as it directly translates the mathematical definition of binomial coefficients into code. It is more intuitive for those with a basic understanding of recursion.
   - Dynamic Programming Approach: Might be slightly more complex to understand initially, especially for those not familiar with dynamic programming concepts. However, it illustrates an important strategy in algorithm design for optimizing performance by trading space for time.

4. Use Cases:
   - Recursive Approach: Suitable for smaller values of n and k, or when the code simplicity is a priority over execution speed and efficiency.
   - Dynamic Programming Approach: Preferred for larger values of n and k, in performance-critical applications where efficiency and scalability are crucial.

In summary, while the recursive approach offers simplicity, the dynamic programming approach provides a significant advantage in terms of computational efficiency, making it the preferred choice for practical applications requiring the calculation of binomial coefficients.
*/
