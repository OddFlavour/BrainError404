package leetCode.juneLeetCodingChallenge.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class LargestDivisibleSubset {
    List<Integer> ans = new ArrayList<>();

    public List<Integer> largestDivisibleSubset(int[] nums) {
        // O(nlogn)
        Arrays.sort(nums);

        // Feels like a knapsack problem
        // You add or don't add
        // O(n^2)
        rec(nums, 0, new Stack<>());

        return ans;
    }

    private void rec(int[] nums, int index, Stack<Integer> current) {
        if (current.size() > ans.size()) {
            ans = new ArrayList<>();
            for (int i : current) {
                ans.add(i);
            }
        }
        if (index >= nums.length) return;

        int last = current.size() > 0 ? current.get(current.size() - 1) : 1;

        // 'nums' is sorted in ascending, so 'last' will always be smaller
        if (nums[index] % last == 0) {
            // With
            current.push(nums[index]);
            rec(nums, index + 1, current);
            current.pop();
        }

        // Without
        rec(nums, index + 1, current);
    }

    public static void main(String[] args) {
        int[][] tests = {
                {2, 1, 3},
                {2, 4, 3, 9, 81},
                {1, 2, 3, 4, 5, 6},
                {}
        };

        for (int i = 0; i < tests.length; i++) {
            LargestDivisibleSubset s = new LargestDivisibleSubset();
            System.out.println(s.largestDivisibleSubset(tests[i]));
        }
    }
}
