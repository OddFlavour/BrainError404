package leetCode.tools;

import java.util.LinkedList;
import java.util.Queue;

public class TreeTools {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static TreeNode convertToTree(Integer[] list) {
        if (list.length == 0) return null;

        int index = 0;

        TreeNode root = new TreeNode(list[index++]);

        Queue<TreeNode> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty() && index < list.length) {
            TreeNode subject = stack.poll();

            if (list[index] != null) {
                subject.left = new TreeNode(list[index]);
            }
            index++;

            if (list[index] != null) {
                subject.right = new TreeNode(list[index]);
            }
            index++;

            if (subject.left != null)
                stack.add(subject.left);
            if (subject.right != null)
                stack.add(subject.right);
        }

        return root;
    }
}
