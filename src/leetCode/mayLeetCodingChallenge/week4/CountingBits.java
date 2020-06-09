package leetCode.mayLeetCodingChallenge.week4;

public class CountingBits {
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];

        int limit = 1;
        int i = 1;

        while (i <= num) {
            if (i == limit) {
                ans[i] = 1;
                limit *= 2;
            } else {
                int offset = limit / 2;
                ans[i] = ans[offset] + ans[i - offset];
            }

            i++;
        }

        return ans;
    }

    public int[] countBitsBestSolution(int num) {
        int[] f = new int[num + 1];

        /**
         * the total number of 1 bits =
         * the number of the prefix 1 bits(exclude the last bit) + the last bit is 1 ? 1 : 0
         */
        for (int i = 1; i <= num; i++) {
            // P(x)=P(x/2)+(xmod2)
            // x / 2 is x >> 1 and x % 2 is x & 1
            f[i] = f[i >> 1] + (i & 1);
        }
        return f;
    }

    public static void main(String[] args) {
        int num = 100000000;
        CountingBits s = new CountingBits();

        long start1 = System.currentTimeMillis();
        s.countBits(num);
        long end1 = System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        s.countBitsBestSolution(num);
        long end2 = System.currentTimeMillis();

        System.out.println(end1 - start1);
        System.out.println(end2 - start2);
    }
}
