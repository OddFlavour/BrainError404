package leetCode.juneLeetCodingChallenge.week5;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // Initialize first row
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        // Initialize first column
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        // Dp
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] tests = {
                {1,1},
                {1,2},
                {2,1},
                {2,2},
                {1,3},
                {2,3},
                {3,1},
                {3,2},
                {3,3}
        };

        for (int i = 0; i < tests.length; i++) {
            UniquePaths s = new UniquePaths();
            System.out.println(s.uniquePaths(tests[i][0], tests[i][1]));
        }
    }
}
