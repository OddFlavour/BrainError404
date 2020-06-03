package leetCode.mayLeetCodingChallenge.week3;

public class MaximumSumCircularSubarray {
    int ans = Integer.MIN_VALUE;

    public int maxSubarraySumCircular(int[] A) {
        int n = A.length;

        // Construct pSums from left to right
        int[] pSums = new int[n + 1];
        int currSum = 0;
        for (int i = 1; i <= n; i++) {
            currSum += A[i - 1];
            pSums[i] = Math.max(pSums[i - 1], currSum);
        }

        int[] dp = new int[n];
        int rightSum = 0;

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = i < n - 1 ? Math.max(A[i], dp[i + 1] + A[i]) : A[i];
            rightSum += A[i];

            ans = Math.max(ans, Math.max(dp[i], pSums[i] + rightSum));
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] tests = {
                {1, -2, 3, -2},
        };

        for (int i = 0; i < tests.length; i++) {
            MaximumSumCircularSubarray s = new MaximumSumCircularSubarray();
            System.out.println(s.maxSubarraySumCircular(tests[i]));
        }
    }
}
