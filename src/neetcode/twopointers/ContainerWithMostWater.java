package neetcode.twopointers;

/**
 * https://neetcode.io/problems/max-water-container?list=neetcode150
 */
public class ContainerWithMostWater {
    static class Solution {
        public int maxArea(int[] heights) {
            int max = 0;
            int l = 0, r = heights.length - 1;

            while (l < r) {
                int area = Math.min(heights[l], heights[r]) * (r - l);

                if (area > max) {
                    max = area;
                }

                if (heights[l] <= heights[r]) {
                    l++;
                } else {
                    r--;
                }
            }

            return max;
        }
    }

    public static void main(String[] args) {
        int[][] ts = {
                { 2, 5, 1, 5, 2 }
        };

        Solution s = new Solution();

        for (int[] t : ts) {
            System.out.println(s.maxArea(t));
        }
    }
}
