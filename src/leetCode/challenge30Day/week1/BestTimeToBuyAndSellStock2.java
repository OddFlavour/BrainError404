package leetCode.challenge30Day.week1;

public class BestTimeToBuyAndSellStock2 {
    public int maxProfit(int[] prices) {
        int profit = 0;

        int last = 0;
        int lastHighest = -1;

        int length = prices.length;
        for (int i = 1; i < length; i++) {
            if (lastHighest == -1) {
                // the current 'last' is not a good buy
                if (prices[i] <= prices[last]) {
                    last = i;
                } else {
                    lastHighest = i;
                }
            } else {
                // sell out
                if (prices[i] <= prices[lastHighest]) {
                    profit += prices[lastHighest] - prices[last];
                    last = i;
                    lastHighest = -1;
                } else {
                    lastHighest = i;
                }
            }
        }

        // remember to sell out if we have been saving this whole time
        if (lastHighest != -1) {
            profit += prices[lastHighest] - prices[last];
        }

        return profit;
    }

    public int recurseNSquared(int[] prices, int start) {
        int maxProfits = 0;
        // Consider every index as a possible starting
        for (int i = start; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];

                if (profit > 0) {
                    int futureProfits = recurseNSquared(prices, j + 1);

                    if (profit + futureProfits > maxProfits) {
                        maxProfits = profit + futureProfits;
                    }
                }
            }
        }

        return maxProfits;
    }

    public static void main(String[] args) {
        int[] t1 = {7, 1, 5, 3, 6, 4};
        int[] t2 = {1, 2, 3, 4, 5};
        int[] t3 = {7, 6, 4, 3, 1};
        int[] t4 = {1};
        int[] t5 = {1, 7, 7, 4, 6, 0};
        int[] t6 = {3,2,6,5,0,3};

        BestTimeToBuyAndSellStock2 s = new BestTimeToBuyAndSellStock2();
        System.out.println(s.maxProfit(t1));
        System.out.println(s.maxProfit(t2));
        System.out.println(s.maxProfit(t3));
        System.out.println(s.maxProfit(t4));
        System.out.println(s.maxProfit(t5));
        System.out.println(s.maxProfit(t6));
    }
}
