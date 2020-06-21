package leetCode.mayLeetCodingChallenge.week5;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();

        if (m == 0) return n;
        if (n == 0) return m;

        char[] w1 = word1.toCharArray(), w2 = word2.toCharArray();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (w1[i] == w2[j]) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    dp[i + 1][j + 1] = Math.min(dp[i][j + 1] + 1,
                            Math.min(dp[i + 1][j] + 1, dp[i][j] + 1));
                }

//                System.out.printf("From %s to %s: %d\n", word1.substring(0, i + 1), word2.substring(0, j + 1), dp[i + 1][j + 1]);
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String[][] tests = {
                {"horse", "ros"},
                {"horse", "ors"},
                {"abo", "ao"},
                {"ao", "o"},
                {"intention", "execution"},
                {"waterbottle", "robot"},
                {"", "a"},
                {"a", ""},
                {"ab", "acd"},
                {"rbt", "rob"},
                {"te", "rot"},
                {"sea", "ate"},
                {"sea", "eat"}
        };

        int[] answers = {
                3,
                2,
                1,
                1,
                5,
                8,
                1,
                1,
                2,
                2,
                3,
                3,
                2
        };

        for (int i = 0; i < tests.length; i++) {
            EditDistance s = new EditDistance();
            int ans = s.minDistance(tests[i][0], tests[i][1]);
            System.out.printf("%d, %s\n", ans, ans == answers[i]);
        }
    }
}
