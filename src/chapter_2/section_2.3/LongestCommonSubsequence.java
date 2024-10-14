
public class LongestCommonSubsequence {

    public static String lcs(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m+1][n+1];
        String[][] lcs = new String[m+1][n+1];

        // Initialize the lcs table
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                lcs[i][j] = ""; 
            }
        }

        // Build the dp table and simultaneously construct the LCS in the lcs array
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    lcs[i][j] = lcs[i-1][j-1] + s.charAt(i-1); // Append the matching character
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    // Choose the longer of the two subsequences
                    lcs[i][j] = dp[i-1][j] >= dp[i][j-1] ? lcs[i-1][j] : lcs[i][j-1];
                }
            }
        }

        return lcs[m][n]; 
    }
    /*
     * Given three strings, write a
program that computes the longest common subsequence of the three strings
     */
    public static int lcsOfThreeStrings(String str1, String str2, String str3) {
        int len1 = str1.length();
        int len2 = str2.length();
        int len3 = str3.length();
    
        int[][][] dp = new int[len1+1][len2+1][len3+1];
    
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                for (int k = 0; k <= len3; k++) {
                    if (i == 0 || j == 0 || k == 0) {
                        dp[i][j][k] = 0; // Base case: one string is empty
                    } else if (str1.charAt(i-1) == str2.charAt(j-1) && str1.charAt(i-1) == str3.charAt(k-1)) {
                        // If current character matches in all three strings, add 1 to result
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    } else {
                        // Otherwise, take the maximum of all possible two string LCS calculations
                        dp[i][j][k] = Math.max(Math.max(dp[i-1][j][k], dp[i][j-1][k]), dp[i][j][k-1]);
                    }
                }
            }
        }
    
        return dp[len1][len2][len3]; 
    }
    
    public static void main(String[] args) {
        String s = "ACGGCGGATACG";
        String t = "GGCACCACG";
        System.out.println("LCS: " + lcs(s, t));
    }
}
