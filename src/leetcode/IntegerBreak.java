package leetcode;

/**
 * https://leetcode.com/problems/integer-break/
 */
public class IntegerBreak {
    static class Solution {
        int[] dp;

        public int integerBreak(int n) {
            this.dp = new int[n];

            int max = 0;
            for (int i = 1; i < n; i++) {
                max = Math.max(helper(i) * helper(n - i), max);
            }

            return max;
        }

        private int helper(int n) {
            if (n <= 1) {
                return n;
            }

            int max = 0;
            for (int i = 1; i < n; i++) {
                int left = dp[i] > 0 ? dp[i] : helper(i);
                int right = dp[n - i] > 0 ? dp[n - i] : helper(n - i);

                max = Math.max(left * right, max);
            }

            dp[n] = Math.max(Math.max(n, max), dp[n]);

            return dp[n];
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        s.integerBreak(5);
    }
}
