package leetCode.challenge30Day.week2;

import java.util.Stack;
import java.util.regex.Pattern;

public class BackspaceStringCompare {
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> cleansedS = cleanse(S);
        Stack<Character> cleansedT = cleanse(T);

        return compareCleansedStrings(cleansedS, cleansedT);
    }

    public Stack<Character> cleanse(String x) {
        Stack<Character> stack = new Stack<>();

        int length = x.length();
        for (int i = 0; i < length; i++) {
            char currChar = x.charAt(i);
            if (currChar == '#') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(currChar);
            }
        }

        return stack;
    }

    public boolean compareCleansedStrings(Stack a, Stack b) {
        while (!a.isEmpty()) {
            try {
                if (a.pop() != b.pop())
                    return false;
            } catch (Exception e) {
                return false;
            }
        }

        if (!b.isEmpty()) return false;

        return true;
    }

    public static void main(String[] args) {
        String[] t1 = { "ab#c", "ad#c" };
        String[] t2 = { "ab##", "c#d#" };
        String[] t3 = { "a##c", "#a#c" };
        String[] t4 = { "a#c", "b" };
        String[] t5 = { "", "aaa##"};
        String[] t6 = {"y#fo##f", "y#f#o##f"};

        BackspaceStringCompare s = new BackspaceStringCompare();
//        System.out.println(s.backspaceCompare(t1[0], t1[1]));
//        System.out.println(s.backspaceCompare(t2[0], t2[1]));
//        System.out.println(s.backspaceCompare(t3[0], t3[1]));
//        System.out.println(s.backspaceCompare(t4[0], t4[1]));
//        System.out.println(s.backspaceCompare(t5[0], t5[1]));
        System.out.println(s.backspaceCompare(t6[0], t6[1]));
    }
}
