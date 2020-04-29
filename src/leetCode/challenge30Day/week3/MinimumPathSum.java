package leetCode.challenge30Day.week3;

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;

        int[][] dp = new int[grid.length][grid[0].length];

        int[][] cases = {
                {-1, 0},
                {0, -1}
        };

        dp[0][0] = grid[0][0];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (dp[i][j] == 0) {
                    for (int[] c : cases) {
                        int a = i + c[0];
                        int b = j + c[1];

                        if (valid(grid, a, b)) {
                            int replacement = dp[a][b] + grid[i][j];
                            if (dp[i][j] == 0) dp[i][j] = replacement;
                            else dp[i][j] = min(dp[i][j], replacement);
                        }
                    }
                }
            }
        }

        return dp[grid.length - 1][grid[0].length - 1];
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

    public boolean valid(int[][] grid, int i, int j) {
        return i >= 0 && i < grid.length
                && j >= 0 && j < grid[0].length;
    }
}
