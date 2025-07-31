package neetcode.twopointers;

/**
 * https://leetcode.com/problems/trapping-rain-water/description/
 * https://neetcode.io/problems/trapping-rain-water?list=neetcode150
 */
public class TrappingRainWater {
    static class Solution {
        public int trap(int[] height) {
            int answer = 0;

            int[] nextHighest = new int[height.length];
            nextHighest[height.length - 1] = height.length - 1;

            for (int i = height.length - 2; i >= 0; i--) {
                if (height[i] >= height[nextHighest[i + 1]]) {
                    nextHighest[i] = i;
                } else {
                    nextHighest[i] = nextHighest[i + 1];
                }
            }

            for (int i = 1; i < height.length; i++) {
                if (height[i - 1] > height[i]) {
                    int r = i;
                    int max_r = r;
                    while (r < height.length) {
                        if (height[r] > height[max_r]) {
                            max_r = r;

                            if (height[r] > height[i - 1]) {
                                break;
                            }

                            if (nextHighest[r] == r) {
                                break;
                            }
                        }

                        r++;
                    }

                    if (max_r != i) {
                        for (int x = i; x < max_r; x++) {
                            answer += Math.min(height[i - 1], height[max_r]) - height[x];
                        }

                        i = max_r;
                    }
                }
            }

            return answer;
        }

        /**
         * Using prefix maximum and suffix maximum, we can quickly find the highest mountains on left and right side,
         * which then can be used to calculate the water trapped at the current index.
         */
        public int trapOptimal(int[] height) {
            int answer = 0;

            int[] nextHighest = new int[height.length];
            nextHighest[height.length - 1] = height.length - 1;

            for (int i = height.length - 2; i >= 0; i--) {
                if (height[i] >= height[nextHighest[i + 1]]) {
                    nextHighest[i] = i;
                } else {
                    nextHighest[i] = nextHighest[i + 1];
                }
            }

            int[] prevHighest = new int[height.length];
            prevHighest[0] = 0;

            for (int i = 1; i < height.length; i++) {
                if (height[i] >= height[prevHighest[i - 1]]) {
                    prevHighest[i] = i;
                } else {
                    prevHighest[i] = prevHighest[i - 1];
                }
            }

            for (int i = 0; i < height.length; i++) {
                answer += Math.min(height[prevHighest[i]], height[nextHighest[i]]) - height[i];
            }

            return answer;
        }
    }

    public static void main(String[] args) {
        int[][] s1 = {
                { 0,1,0,2,1,0,1,3,2,1,2,1 },
                { 4,2,0,3,2,5 },
                { 0 },
                { 1 },
                { 3, 1, 0, 1, 2, 1, 0, 1, 3 },
                { 5,4,1,2 },
                { 2, 1, 2, 3 }
        };

        Solution a = new Solution();

        for (int[] s : s1) {
            System.out.println(a.trap(s));
        }
    }
}
