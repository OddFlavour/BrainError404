package leetCode.juneLeetCodingChallenge.week4;

public class UniqueBinarySearchTrees {
    int[] dp;

    public int numTrees(int n) {
        dp = new int[n + 1];
        dp[0] = 1;

        return helper(n, 0, n - 1);
    }

    private int helper(int n, int left, int right) {
        if (dp[n] != 0) return dp[n];

        int result = 0;

        for (int i = left; i <= right; i++) {
            int combLeft = dp[i - left];
            int combRight = dp[right - i];

            if (combLeft == 0) {
                combLeft = helper(i - left, left, i - 1);
            }

            if (combRight == 0) {
                combRight = helper(right - i, i + 1, right);
            }

            result += combLeft * combRight;
        }

        dp[n] = result;

        return result;
    }

    public static void main(String[] args) {
        final int TEST_COUNT = 10;

        for (int i = 1; i < TEST_COUNT; i++) {
            UniqueBinarySearchTrees s = new UniqueBinarySearchTrees();
            System.out.println(s.numTrees(i));
        }
    }
}
