package leetCode.juneLeetCodingChallenge.week1;

import java.util.ArrayList;
import java.util.List;

public class CoinChange2 {
    public int change(int amount, int[] coins) {
        /*
        In order to avoid duplicates, the coins used should be sorted in order (perhaps largest to smallest)

        For example, there are two ways to make up '3' with the coins '{1, 2}'
        - [1, 2]
        - [2, 1]

        To sort it in order, we'd need to keep track of what coins were used?
        No, we just have to keep track of the largest element (X)
         */

        // We need a list for each possible sum to keep track of the X
        List<Integer>[] sums = new ArrayList[amount + 1];
        for (int i = 0; i < sums.length; i++) sums[i] = new ArrayList<>();
        sums[0].add(0);

        for (int i = 1; i <= amount; i++) {
            for (int c : coins) {
                int result = i - c;
                if (result >= 0) {
                    // Here we will need to use a cached result from before
                    // But we must make sure to follow how it's sorted
                    for (int cached : sums[result]) {
                        if (cached <= c) {
                            sums[i].add(c);
                        }
                    }
                }
            }
        }

        return sums[amount].size();
    }

    public static void main(String[] args) {
        int[] testAmounts = {
                5,
                0,
                0,
        };

        int[][] testCoins = {
                {1, 2, 5},
                {1, 2, 5},
                {}
        };

        for (int i = 0; i < testAmounts.length; i++) {
            CoinChange2 s = new CoinChange2();
            System.out.println(s.change(testAmounts[i], testCoins[i]));
        }
    }
}
