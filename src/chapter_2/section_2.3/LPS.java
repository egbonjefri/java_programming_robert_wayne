// This code finds the longest palindromic subsequence in a given string.
// It works by using dynamic programming to store solutions to overlapping
// subproblems and building larger palindromes from smaller ones.
//
// Key concepts:
// - Overlapping Subproblems: Substrings share overlapping characters,
//   so solving smaller subproblems first can avoid redundant calculations.
// - Dynamic Programming Table (dp): Stores lengths of longest palindromes
//   for substrings starting at different indices.
// - Base Case (Length 2): If first and last characters match, it's a palindrome.
// - Recursive Case (Length > 2):
//   - If first and last characters match, build a larger palindrome from
//     a smaller one by adding 2 (for the first and last characters) to the
//     length stored in `dp` for the substring between those characters.
//   - If characters don't match, compare longest palindromes in adjacent
//     substrings and copy the larger value to `dp`.




public class LPS {
    public static void main(String[] args) {
        String text = "character";
        System.out.println(longestPalindromicSubsequence(text));
      }
      public static String longestPalindromicSubsequence(String text) {
        int n = text.length();
        int[][] dp = new int[n][n];
        String[][] sequence = new String[n][n];    
        // Base cases: length 1 and 2 palindromes
        for (int i = 0; i < n; i++) {
            //dp[i][i] represents the length of a palindrome of length 1 (a single character) starting at index i. 
            //Since a single character is a palindrome by itself, its length is 1
          dp[i][i] = 1;
          sequence[i][i] = String.valueOf(text.charAt(i));
        // Checking if i < n - 1 is absolutely essential because we're filling dp[i][i+1] in the next step
          if (i < n - 1 && text.charAt(i) == text.charAt(i + 1)) {
            //dp[i][i + 1] represents the length of a palindrome starting at index i and ending at index i + 1. 
            //This is specifically checking for palindromes of length 2.
            dp[i][i + 1] = 2;
            sequence[i][i + 1] = "" + text.charAt(i) + text.charAt(i + 1);
        }
        }
    
        // Build solutions for larger palindromes
        for (int length = 3; length <= n; length++) {
          for (int start = 0; start <= n - length; start++) {
            int end = start + length - 1;
    
            if (text.charAt(start) == text.charAt(end)) {
              dp[start][end] = dp[start + 1][end - 1] + 2;
              sequence[start][end] = "" + text.charAt(start) + sequence[start + 1][end - 1] + text.charAt(end);
            } else {
              dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
              if (dp[start][end] == dp[start][end - 1]) {
                sequence[start][end] = sequence[start][end - 1];
            } else {
                sequence[start][end] = sequence[start + 1][end];
            }
            }
          }
        }
    
        return sequence[0][n - 1];
      }
}
