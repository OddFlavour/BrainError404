package neetcode.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://neetcode.io/problems/generate-parentheses?list=neetcode150
 */
public class GenerateParentheses {
    static class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> answer = new ArrayList<>();

            helper(n, 0, new Stack<>(), answer);

            return answer;
        }

        private void helper(int remainingStart, int remainingEnd, Stack<Character> current, List<String> answer) {
            if (remainingStart < 0) {
                return;
            }

            if (remainingStart > 0) {
                current.push('(');
                helper(remainingStart - 1, remainingEnd + 1, current, answer);
                current.pop();
            }

            if (remainingEnd > 0) {
                if (!current.isEmpty()) {
                    current.push(')');
                    helper(remainingStart, remainingEnd - 1, current, answer);
                    current.pop();
                }
            }

            if (remainingStart == 0 && remainingEnd == 0) {
                StringBuilder sb = new StringBuilder();
                for (Character c : current) {
                    sb.append(c);
                }

                answer.add(sb.toString());
            }
        }
    }

    public static void main(String[] args) {
        int[] ts = {
                2,
                3
        };

        for (int t : ts) {
            Solution s = new Solution();
            System.out.println(s.generateParenthesis(t));
        }
    }
}
