package leetCode.challenge30Day.week4;

/**
 * Greedy method
 * ---
 * If we jump to a point and that point has more energy than what we currently have, then we can stop there and start again
 *
 * E.g [3, 2, 2, 0, 1]
 * If we jump to index 2, we would have used up 2 energy, leaving us with 1 energy left. If we stop and start at index 2,
 * then our energy will now be 2 which is bigger than 1.
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int n = nums.length;

        int max = 0;
        for (int i = 0; i < n - 1; i++) {
            max--;
            if (nums[i] == 0 && max < 1) return false;
            if (max - nums[i] < 0) max = nums[i];
        }
        return true;
    }
}
