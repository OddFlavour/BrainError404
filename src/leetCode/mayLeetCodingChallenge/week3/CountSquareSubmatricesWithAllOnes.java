package leetCode.mayLeetCodingChallenge.week3;

public class CountSquareSubmatricesWithAllOnes {
    int ans = 0;

    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    dp[i + 1][j + 1] = Math.min(dp[i][j + 1], Math.min(dp[i][j], dp[i + 1][j])) + 1;
                    ans += dp[i+1][j+1];
                }
            }
        }

        return ans;
    }
}
