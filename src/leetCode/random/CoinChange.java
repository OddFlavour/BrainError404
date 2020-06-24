package leetCode.random;

import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        long[] sums = new long[amount + 1];
        Arrays.fill(sums, -1);
        sums[0] = 0;

        for (int i = 0; i <= amount; i++) {
            for (int c : coins) {
                if (sums[i] != -1 && i + c >= 0 && i + c < sums.length) {
                    sums[i + c] = sums[i + c] == -1 ? sums[i] + 1 : Math.min(sums[i + c], sums[i] + 1);
                }
            }
        }

        return (int) sums[amount];
    }

    public static void main(String[] args) {
        int[][] testCoins = {
                {1, 2, 5},
                {2},
                {1, 2, 5},
                {},
                {5},
                {1,2147483647}
        };

        int[] testAmount = {
                11,
                3,
                10,
                10,
                11,
                2
        };

        for (int i = 0; i < testCoins.length; i++) {
            CoinChange s = new CoinChange();
            System.out.println(s.coinChange(testCoins[i], testAmount[i]));
        }
    }
}
