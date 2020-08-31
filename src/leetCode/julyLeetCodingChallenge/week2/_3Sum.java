package leetCode.julyLeetCodingChallenge.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _3Sum {
    // Intuition: three pointers, in order to avoid duplicates, once a sum of 0 is found OR a current iteration has ended, then run while loop to skip duplicate values
    // E.g: 1, 1, 1, 0, -2, -2, -2
    //      ^  ^                 ^
    // Three pointers sum to 0. If we simply increment p[1] and p[2] by one, then we'll get duplicate. So we must skip over duplicate numbers
    // to result in the following:
    //      1, 1, 1, 0, -2, -2, -2
    //      ^        ^
    //               ^
    // Now p[1] and p[2] will be on the same index, so the current iteration is done. Now If p[1] increments by one, then we'll get duplicate again.
    // So skip over duplicate numbers to result in the following new set of pointers:
    //      1, 1, 1, 0, -2, -2, -2
    //               ^   ^       ^
    // Continue until there are less than three numbers between p[0] and p[2] (inclusive)
    // A.k.a: continue if (p[2] - p[0]) < 2
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(nums);

        int n = nums.length;
        int temp = 0;
        int[] p = {0, 1, n - 1};

        while (p[0] + 1 < p[2]) {
            while (p[1] < p[2]) {
                int sums = getSum(nums, p);

                if (sums < 0) {
                    p[1]++;
                } else if (sums > 0) {
                    p[2]--;
                } else {
                    List<Integer> addition = new ArrayList<>();

                    for (int pointer : p) {
                        addition.add(nums[pointer]);
                    }

                    ans.add(addition);

                    // For p[1]
                    temp = p[1];
                    while (temp < (n - 1) && nums[temp] == nums[p[1]]) temp++;

                    p[1] = temp;

                    // For p[2]
                    temp = p[2];
                    while (temp > 0 && nums[temp] == nums[p[2]]) temp--;

                    p[2] = temp;
                }
            }

            // For p[0]
            temp = p[0];
            while (temp < (n - 1) && nums[temp] == nums[p[0]]) temp++;

            p[0] = temp;

            // Update other pointers
            p[1] = p[0] + 1;
            p[2] = n - 1;
        }

        return ans;
    }

    private int getSum(int[] nums, int[] p) {
        return nums[p[0]] + nums[p[1]] + nums[p[2]];
    }

    public static void main(String[] args) {
        int[][] tests = {
                {-1,-1,-1,0,0,0,1,1,1},
                {-1,0,1,2,-1,-4},
                {1, 2, 3, 4, 5}
        };

        for (int i = 0; i < tests.length; i++) {
            _3Sum s = new _3Sum();
            s.threeSum(tests[i]);
        }
    }
}
