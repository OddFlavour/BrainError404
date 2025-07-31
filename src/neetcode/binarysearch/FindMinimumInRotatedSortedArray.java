package neetcode.binarysearch;

public class FindMinimumInRotatedSortedArray {
    static class Solution {
        public int findMin(int[] nums) {
            int ans = Integer.MAX_VALUE;
            int l = 0, r = nums.length - 1;

            while (l <= r) {
                int m = l + (r - l) / 2;
                ans = Math.min(ans, nums[m]);

                if (nums[l] > nums[m]) {
                    r = m - 1;
                } else if (nums[r] < nums[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        int[][] ts = {
//                {1, 2, 0},
//                {1, 2, 3, 4, 0},
//                {0, 1, 2, 3, 4},
//                {4, 0, 1, 2, 3},
                {4, 5, 6, 7}
        };

        for (int[] t : ts) {
            Solution s = new Solution();
            System.out.println(s.findMin(t));
        }
    }
}
