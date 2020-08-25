package leetCode.julyLeetCodingChallenge.week1;

public class HammingDistance {
    public int hammingDistance(int x, int y) {
        int ans = 0;

        while (x != 0 || y != 0) {
            int a = x & 1;
            int b = y & 1;
            if (a != b) ans ++;

            x >>= 1;
            y >>= 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] tests = {
                {1, 4},
                {0, 4},
                {0, 15}
        };

        for (int i = 0; i < tests.length; i++) {
            HammingDistance s = new HammingDistance();
            System.out.println(s.hammingDistance(tests[i][0], tests[i][1]));
        }
    }
}
