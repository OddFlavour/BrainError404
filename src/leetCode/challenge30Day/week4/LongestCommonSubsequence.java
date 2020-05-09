package leetCode.challenge30Day.week4;

public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    // in the case that we are worried text1[i] or text2[j] has been used before (e.g {"aa", "a"} | i=1, j=0 | j=0 has been used by i=0 before)
                    // then we can just take dp[i][j] which would ignore text1[i] and text2[j], so they couldn't have been used
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        return dp[m][n];
    }
}
