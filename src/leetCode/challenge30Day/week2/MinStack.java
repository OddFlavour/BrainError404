package leetCode.challenge30Day.week2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MinStack {

    class MinStackNode {
        int min;
        int value;

        public MinStackNode(int min, int value) {
            this.min = min;
            this.value = value;
        }
    }

    Stack<MinStackNode> stack;

    /** initialize your data structure here. */
    public MinStack() {
        this.stack = new Stack<>();
    }

    public void push(int x) {
        int newMin = x;
        if (!this.stack.isEmpty()) {
            int currMin = this.getMin();
            if (currMin < newMin) {
                newMin = currMin;
            }
        }

        MinStackNode plate = new MinStackNode(newMin, x);
        this.stack.push(plate);
    }

    public void pop() {
        this.stack.pop();
    }

    public int top() {
        return this.stack.peek().value;
    }

    public int getMin() {
        return this.stack.peek().min;
    }

    public static void main(String[] args) {
        MinStack s = new MinStack();

        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        assertTrue(minStack.getMin(), -3);
        minStack.pop();
        assertTrue(minStack.top(), 0);
        assertTrue(minStack.getMin(), -2);
    }

    public static void assertTrue(int a, int b) {
        System.out.printf("%d == %d\n", a, b);
    }
}
