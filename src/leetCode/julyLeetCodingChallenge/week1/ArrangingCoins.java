package leetCode.julyLeetCodingChallenge.week1;

public class ArrangingCoins {
    public int arrangeCoinsBigN(int n) {
        int ans = 0;
        while ((n - (ans + 1)) >= 0) {
            n -= (ans++ + 1);
        }

        return ans;
    }

    public int arrangeCoins(int n) {
        // (ans * (1 + ans)) / 2 = n
        double x1 = (-1 + Math.sqrt(1 - 4 * -2.0 * n)) / 2;
//        double x2 = (-1 - Math.sqrt(1 - 4 * -2.0 * n)) / 2;

//        System.out.println(x1 + " " + x2);

        return (int) x1;
    }

    public static void main(String[] args) {
        final int TEST_COUNT = 1804289384;

        for (int i = 1804289383; i < TEST_COUNT; i++) {
            ArrangingCoins s = new ArrangingCoins();
            boolean result = s.arrangeCoins(i) == s.arrangeCoinsBigN(i);

            if (!result) {
                System.out.println(i);
            }
        }
    }
}
