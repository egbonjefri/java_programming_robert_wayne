public class HousePainter {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }

        // Number of houses.
        int n = costs.length;
        
        // DP array to store the minimum cost of painting up to house i with color j.
        int[][] dp = new int[n][3];

        // Initial costs for the first house.
        for (int j = 0; j < 3; j++) {
            dp[0][j] = costs[0][j];
        }

        // Fill the DP array.
        for (int i = 1; i < n; i++) {
            dp[i][0] = costs[i][0] + Math.min(dp[i-1][1], dp[i-1][2]); // Red
            dp[i][1] = costs[i][1] + Math.min(dp[i-1][0], dp[i-1][2]); // Green
            dp[i][2] = costs[i][2] + Math.min(dp[i-1][0], dp[i-1][1]); // Blue
        }

        // The answer is the minimum cost to paint the last house, which is the minimum of dp[n-1][0], dp[n-1][1], and dp[n-1][2].
        return Math.min(Math.min(dp[n-1][0], dp[n-1][1]), dp[n-1][2]);
    }

    public static void main(String[] args) {
        HousePainter painter = new HousePainter();
        int[][] costs = {
            {17, 2, 17},
            {16, 16, 5},
            {14, 3, 19}
        };
        System.out.println("Minimum cost to paint all houses: " + painter.minCost(costs));
    }
}
