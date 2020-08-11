package leetCode.juneLeetCodingChallenge.week4;

import java.util.Arrays;

public class PerfectSquares {
    /*
    Almost same DP solution as CoinChange, at first I thought keeping an array of valid squares so I can replicate the
    CoinChange problem. But it took too long to form the array.

    Then instead of computing the valid squares, I just added an inner loop that loops through all valid squares until
    an invalid one is found.

    Note to self: Using Math.pow slows down a lot, when possible just use '*'
     */
    public int numSquares(int n) {
//        List<Integer> squares = new ArrayList<>();
//
//        int curr = 1;
//        int pow;
//        while ((pow = (int) Math.pow(curr++, 2)) <= n) {
//            squares.add(pow);
//        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 0; i <= n; i++) {
//            for (int square : squares) {
            int curr = 1;
            int pow;
            while ((pow = curr * curr) <= i) {
                if (dp[i] == -1 || dp[i] > (dp[i - pow] + 1)) {
                    dp[i] = dp[i - pow] + 1;
                }
                curr++;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        final int TEST_COUNT = 20;

        for (int i = 1; i < TEST_COUNT; i++) {
            PerfectSquares s = new PerfectSquares();
            System.out.printf("%d: %d\n", i, s.numSquares(i));
        }
    }
}
