
/*
 * Write a program LongestPalindromic-
Subsequence that takes a string as a command-line argument and determines the
longest subsequence of the string that is a palindrome (the same when read forward
or backward). Hint : Compute the longest common subsequence of the string and
its reverse.
 */

public class LongestPalindromicSubsequence {

    // Function to compute the LCS of two strings
    public static String longestCommonSubsequence(String s1, String s2) {
        int m = s1.length();
        int k = s2.length();
        int[][] dp = new int[m + 1][k + 1];

        // Build the LCS table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= k; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Reconstruct the LCS from the dp table
        int i = m, j = k;
        StringBuilder lcs = new StringBuilder();
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcs.reverse().toString();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java LongestPalindromicSubsequence <string>");
            return;
        }

        String input = args[0];
        String reversedInput = new StringBuilder(input).reverse().toString();
        String longestPalindromicSubsequence = longestCommonSubsequence(input, reversedInput);

        System.out.println("Longest Palindromic Subsequence: " + longestPalindromicSubsequence);
    }
}
