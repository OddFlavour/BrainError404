package neetcode.arraysandhashing;

/**
 * https://neetcode.io/problems/duplicate-integer
 */
public class ContainsDuplicate {
    static class Solution {
        public boolean hasDuplicate(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] == nums[j]) {
                        return true;
                    }
                }
            }

            return false;
        }
    }
}
