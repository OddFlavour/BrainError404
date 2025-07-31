package neetcode.arraysandhashing;

import java.util.HashSet;
import java.util.Set;

/**
 * https://neetcode.io/problems/longest-consecutive-sequence?list=neetcode150
 * https://leetcode.com/problems/longest-consecutive-sequence/
 */
public class LongestConsecutiveSequence {
    static class Solution {
        public int longestConsecutive(int[] nums) {
            Set<Integer> cache = new HashSet<>();

            for (int num : nums) {
                cache.add(num);
            }

            int max = 0;

            for (Integer num : cache) {
                int curr = num;
                int currLength = 0;

                if (cache.contains(curr - 1)) {
                    continue;
                }

                while (cache.contains(curr)) {
                    currLength++;
                    curr++;
                }

                max = Math.max(max, currLength);
            }

            return max;
        }
    }

}
