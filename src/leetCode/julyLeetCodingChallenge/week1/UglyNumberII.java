package leetCode.julyLeetCodingChallenge.week1;

public class UglyNumberII {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;

        int a, b, c;
        a = b = c = 0;

        for (int i = 1; i < n; i++) {
            int result = Math.min(dp[a] * 2, Math.min(dp[b] * 3, dp[c] * 5));

            dp[i] = result;

            if (dp[a] * 2 == result) a++;
            if (dp[b] * 3 == result) b++;
            if (dp[c] * 5 == result) c++;
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] tests = {
                10
        };

        for (int i = 0; i < tests.length; i++) {
            UglyNumberII s = new UglyNumberII();
            System.out.println(s.nthUglyNumber(tests[i]));
        }
    }
}
