package leetCode.mayLeetCodingChallenge.week4;

public class CountingBits {
    public int[] countBits(int num) {
        int digits = Math.max(0, (int) (Math.log(num) / Math.log(2))) + 1;

        int[] fragments = new int[digits];
        int[] ans = new int[num + 1];

        int count = 0;
        for (int i = 1; i <= num; i++) {
            int ind = 0;
            while (fragments[ind] == 1) {
                fragments[ind++] = 0;
                count--;
            }
            fragments[ind] = 1;
            count++;

            ans[i] = count;
        }

        return ans;
    }

    public static void main(String[] args) {
        CountingBits s = new CountingBits();
        int[] ans = s.countBits(1);

        for (int i : ans) {
            System.out.printf("%d ", i);
        }
    }
}
