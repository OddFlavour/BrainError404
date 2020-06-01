package leetCode.mayLeetCodingChallenge.week2;

import java.util.Stack;

public class RemoveKDigits {
    // Runs in O(k * n)
    public String removeKdigits(String num, int k) {
        // Greedy
        for (int i = 0; i < k; i++) {
            int index = 0;

            for (int j = 0; j < num.length(); j++) {
                if (num.charAt(j) < num.charAt(index)) {
                    break;
                } else {
                    index = j;
                }
            }

            num = num.substring(0, index) + num.substring(index + 1);
        }

        num = num.replaceFirst("^0+", "");
        return num.isEmpty() ? "0" : num;
    }

    public String removeKdigitsBigN(String num, int k) {
        // Edge case
        if (k == num.length()) return "0";

        Stack<Character> stack = new Stack<>();

        for (char c : num.toCharArray()) {
            while (!stack.isEmpty()  // Prevents EmptyStackError
                    && k > 0  // Can only remove at max 'k' characters
                    && stack.peek() > c) {  // Following greedy method, remove if previous is larger than current
                stack.pop();
                k--;
            }
            stack.push(c);
        }

        // In the case that 'k != 0', then all the largest values are near the top of the stack
        while (k > 0) {
            stack.pop();
            k--;
        }

        // Build the string
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();

        // Remove leading 0's
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String[] testNums = {
//                "1432219",
//                "10200",
//                "10",
                "12345",
                "12345"
        };

        int[] testKs = {
//                3,
//                1,
//                2,
                3,
                5
        };

        for (int i = 0; i < testNums.length; i++) {
            RemoveKDigits s = new RemoveKDigits();
            System.out.println(s.removeKdigitsBigN(testNums[i], testKs[i]));
        }
    }
}
