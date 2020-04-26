package leetCode.challenge30Day.week3;

import java.util.*;

public class ValidParenthesisString {
    class StackElement {
        char symbol;
        int index;

        public StackElement(char symbol, int index) {
            this.symbol = symbol;
            this.index = index;
        }

        @Override
        public String toString() {
            return symbol + ": " + index;
        }
    }

    public boolean checkValidString(String s) {
        List<Integer> sIndexes = new ArrayList<>();
        int[] lSlide = new int[s.length()];
        int[] rSlide = new int[s.length()];
        Arrays.fill(lSlide, -1);
        Arrays.fill(rSlide, -1);

        Stack<StackElement> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (!stack.isEmpty() && stack.peek().symbol == '(') {
                    stack.pop();
                } else {
                    stack.push(new StackElement(')', i));
                }
            } else if (s.charAt(i) == '('){
                stack.push(new StackElement('(', i));
            }

            if (s.charAt(i) == '*') {
                lSlide[i] = i;
            } else if (i > 0) {
                lSlide[i] = lSlide[i - 1];
            }

            int j = s.length() - 1 - i;
            if (s.charAt(j) == '*') {
                rSlide[j] = j;
            } else if (j < rSlide.length - 1) {
                rSlide[j] = rSlide[j + 1];
            }
        }

        // The stack can look like one of these:
        // - )...(...
        // - )...
        // - (...

        // Form the two lists
        Queue<Integer> openList = new LinkedList<>();
        Stack<Integer> closeStack = new Stack<>();
        while (!stack.isEmpty()) {
            StackElement e = stack.pop();
            if (e.symbol == '(') {
                openList.add(e.index);
            } else if (e.symbol == ')') {
                closeStack.push(e.index);
            }
        }

        // Resolve all of the ')'s then move onto the '('s
        int curr = 0;
        while (!closeStack.isEmpty()) {
            int index = closeStack.pop();
            int result = getSlideResult(index, lSlide);
            if (result == -1) return false;

            // Check boundaries
            int newSlideTo = result - 1;
            if (newSlideTo >= 0) {
                lSlide[result] = newSlideTo;
            } else {
                lSlide[result] = -1;
            }
        }

        // Resolve all the '('s
        while (!openList.isEmpty()) {
            int index = openList.poll();

            int result = getSlideResult(index, rSlide);
            if (result == -1) return false;

            // Check boundaries
            int newSlideTo = result + 1;
            if (newSlideTo < rSlide.length) {
                rSlide[result] = newSlideTo;
            } else {
                rSlide[result] = -1;
            }
        }

        return true;
    }

    public int getSlideResult(int start, int[] slide) {
        if (slide[start] == -1) return -1;

        int slideTo = slide[start];
        if (slide[slideTo] == slideTo) return slideTo;
        else {
            slide[slideTo] = getSlideResult(slideTo, slide);
            return slide[slideTo];
        }
    }
}
