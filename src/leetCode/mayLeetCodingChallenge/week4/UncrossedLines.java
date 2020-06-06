package leetCode.mayLeetCodingChallenge.week4;

// Identical solution to LongestCommonSubsequence
public class UncrossedLines {
    public int maxUncrossedLines(int[] A, int[] B) {
        int[][] dp = new int[A.length + 1][B.length + 1];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] == B[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        return dp[A.length][B.length];
    }

    public static void main(String[] args) {
        int[][] testAs = {
                {1, 4, 2},
                {2, 5, 1, 2, 5},
                {1, 3, 7, 1, 7, 5},
                {3, 4, 1}
        };

        int[][] testBs = {
                {1, 2, 4},
                {10, 5, 2, 1, 5, 2},
                {1, 9, 2, 5, 1},
                {1, 2, 2, 3, 4, 1}
        };

        for (int i = 0; i < testAs.length; i++) {
            UncrossedLines s = new UncrossedLines();
            System.out.println(s.maxUncrossedLines(testAs[i], testBs[i]));
        }
    }
}
