package leetCode.juneLeetCodingChallenge.week4;

import java.util.HashSet;
import java.util.Set;

public class SingleNumberII {
    public int singleNumberSlow(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        long curr = 0;
        long total = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!seen.contains(nums[i])) {
                seen.add(nums[i]);

                total += (long) nums[i] * 3;
            }

            curr += nums[i];
        }

        return (int) ((total - curr) / 2);
    }

    public int singleNumber(int[] nums) {
        int x1 = 0, x2 = 0, mask = 0;

        for (int i : nums) {
            x2 ^= x1 & i;
            x1 ^= i;
            mask = ~(x1 & x2);
            x2 &= mask;
            x1 &= mask;
        }

        return x2;
    }

    public static void main(String[] args) {
        int[][] tests = {
                {2, 2, 3, 2},
                {0, 1, 0, 1, 0, 1, 99},
                {43,16,45,89,45,-2147483648,45,2147483646,-2147483647,-2147483648,43,2147483647,-2147483646,-2147483648,89,-2147483646,89,-2147483646,-2147483647,2147483646,-2147483647,16,16,2147483646,43},
                {1, 2, 3, 3, 2, 3, 2},
        };

        for (int i = 0; i < tests.length; i++) {
            SingleNumberII s = new SingleNumberII();
            System.out.println(s.singleNumber(tests[i])
//                    == s.singleNumberSlow(tests[i])
            );
        }

    }
}
