package neetcode.twopointers;

import java.util.*;

/**
 * https://neetcode.io/problems/three-integer-sum?list=neetcode150
 */
public class ThreeSum {
    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Set<List<Integer>> answer = new HashSet<>();

            Arrays.sort(nums);

            for (int i = 0; i < nums.length - 2; i++) {
                int l = i + 1, r = nums.length - 1;

                while (l < r) {
                    int sum = nums[i] + nums[l] + nums[r];
                    if (sum > 0) {
                        r--;
                    } else if (sum < 0) {
                        l++;
                    } else {
                        answer.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        r--;
                        l++;
                    }
                }
            }

            return new ArrayList<>(answer);
        }
    }

    public static void main(String[] args) {
        int[][] tests = {
                { -1,0,1,2,-1,-4 },
                { 0,1,1 },
                { 0,0,0 },
                { -2, -2, 0, 0, 2, 2 },
                { -2,0,1,1,2 }
        };

        Solution s = new Solution();

        for (int[] test : tests) {
            System.out.println(s.threeSum(test));
        }
    }
}
