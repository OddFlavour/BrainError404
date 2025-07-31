package neetcode.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://neetcode.io/problems/daily-temperatures?list=neetcode150
 */
public class DailyTemperatures {
    static class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int[] ans = new int[temperatures.length];

            Stack<IndexedInteger> stack = new Stack<>();

            for (int i = 0; i < temperatures.length; i++) {
                while (!stack.isEmpty()) {
                    IndexedInteger top = stack.peek();

                    if (top.value < temperatures[i]) {
                        stack.pop();

                        ans[top.index] = i - top.index;
                    } else {
                        break;
                    }
                }

                stack.push(new IndexedInteger(i, temperatures[i]));
            }

            return ans;
        }
    }

    static class IndexedInteger implements Comparable<IndexedInteger> {
        int index;
        int value;

        public IndexedInteger(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public int compareTo(IndexedInteger other) {
            return this.value - other.value;
        }
    }

    public static void main(String[] args) {
        int[][] ts = {
                { 30,38,30,36,35,40,28 }
        };

        for (int[] t : ts) {
            Solution s = new Solution();

            System.out.println(Arrays.toString(s.dailyTemperatures(t)));
        }
    }
}
