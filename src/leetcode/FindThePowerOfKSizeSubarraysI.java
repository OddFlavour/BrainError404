package leetcode;

/**
 * https://leetcode.com/problems/find-the-power-of-k-size-subarrays-i/
 */
public class FindThePowerOfKSizeSubarraysI {
    static class Solution {
        public int[] resultsArray(int[] nums, int k) {
            // 1 2 3 1 1 2 3
            int n = nums.length;

            int[] out = new int[n - k + 1];
            int kCount = 0;
            int curr = 0;

            for (int i = 0; i < n; i++) {
                if (nums[i] == curr + 1) {
                    kCount = Math.min(kCount + 1, k);
                } else {
                    kCount = 1;
                }

                curr = nums[i];

                if (i >= k - 1) {
                    if (kCount == k) {
                        out[i - k + 1] = curr;
                    } else {
                        out[i - k + 1] = -1;
                    }
                }
            }

            return out;
        }
    }
}
