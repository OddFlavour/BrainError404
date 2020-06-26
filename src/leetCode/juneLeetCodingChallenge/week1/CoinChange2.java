package leetCode.juneLeetCodingChallenge.week1;

public class CoinChange2 {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        /*
        Explanation from 'albertaiq'
        "I think one thing you didn't explain is that why the outer loop is the coins, not the amount.
        The reason behind that is that as you mentioned, the problem is to find the total number of combinations,
        not the permutations. If the outer loop is the amount, then the same combination will be counted
        multiple times because they can come in in different orders. By letting the coins to be the outer loops,
        one assures that for any valid combination, the order of each coin will always be the same as their
        order in coins, so there can be no duplicates."
         */

        // To speed up further, replace for-each with regular for
        for (int c : coins) {
            // We can set 'i = c' here to avoid accounting for 'i - c < 0'
            for (int i = c; i <= amount; i++) {
                    dp[i] += dp[i - c];
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        int[] testAmounts = {
                5,
                0,
                0,
                8,
                500
        };

        int[][] testCoins = {
                {1, 2, 5},
                {1, 2, 5},
                {},
                {2, 4},
                {3, 5, 7, 8, 9, 10, 11}
        };

        for (int i = 0; i < testAmounts.length; i++) {
            CoinChange2 s = new CoinChange2();
            System.out.println(s.change(testAmounts[i], testCoins[i]));
        }
    }
}
