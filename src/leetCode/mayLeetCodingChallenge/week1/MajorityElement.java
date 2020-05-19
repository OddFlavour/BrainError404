package leetCode.mayLeetCodingChallenge.week1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    public int majorityElement(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> count = new HashMap<>();

        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);

            if (count.get(num) > n / 2) {
                return num;
            }
        }

        // Default, nums will be non-empty
        return nums[0];
    }

    /**
     * This should not be faster than above in worst case, since nlogn > n
     * Average case this should be faster
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public static void main(String[] args) {
        int[][] tests = {
                {3,2,3},
                {2,2,1,1,1,2,2},
                {1},
                {1,2,2},
                {1, 59, 20, 20, 20, 1, 20, 20}
        };

        for (int[] test : tests) {
            MajorityElement s = new MajorityElement();
            System.out.println(s.majorityElement(test));
        }
    }
}
