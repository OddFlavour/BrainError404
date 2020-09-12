package leetCode.julyLeetCodingChallenge.week2;

import leetCode.tools.TreeTools.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumWidthOfBinaryTree {
    class Pair {
        TreeNode node;
        int i;

        public Pair(TreeNode a, int b) {
            this.node = a;
            this.i = b;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        int ans = 0;

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 1));

        int accum = 1;

        while (!q.isEmpty()) {
            int size = q.size();

            int left = 1, right = 0;
            boolean leftFound = false;

            for (int i = 0; i < size; i++) {
                Pair curr = q.poll();

                if (curr == null) continue;

                if (!leftFound) {
                    left = curr.i;
                    leftFound = true;
                } else {
                    right = curr.i;
                }

                q.offer(new Pair(curr.node.left, curr.i * 2));
                q.offer(new Pair(curr.node.right, curr.i * 2 + 1));
            }

            ans = Math.max(right - left + 1 - (2 * (accum - 1)), ans);
            accum *= 2;
        }

        return ans;
    }
}
