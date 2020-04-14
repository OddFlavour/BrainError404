package leetCode.challenge30Day.week2;

public class ContiguousArray {

    public int findMaxLength(int[] nums) {
        int ans = 0;
        // loop through each possible start
        for (int i = 0; i < nums.length; i++) {
            int ones = 0;
            int zeroes = 0;
            // from possible start, loop through list
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 1) {
                    ones++;
                } else {
                    zeroes++;
                }

                if (ones == zeroes) {
                    if (ones + zeroes > ans) {
                        ans = ones + zeroes;
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] t1 = { 0,1 };
        int[] t2 = { 0,1,0 };
        int[] t3 = {
                0,0,0,0,0,0,0,0,0,0,
                1,1,1,1,1,1,1,1,1
        };
        int[] t4 = { 0 };
        int[] t5 = { };
        int[] t6 = { 0, 1, 0, 0, 1, 0, 0, 1 };

        ContiguousArray s = new ContiguousArray();
        assertTrue(s.findMaxLength(t1), 2);
        assertTrue(s.findMaxLength(t2), 2);
        assertTrue(s.findMaxLength(t3), 18);
        assertTrue(s.findMaxLength(t4), 0);
        assertTrue(s.findMaxLength(t5), 0);
        assertTrue(s.findMaxLength(t6), 4);
    }

    public static void assertTrue(int a, int b) {
        System.out.printf("%d == %d\n", a, b);
    }
}
