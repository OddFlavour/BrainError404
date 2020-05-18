package leetCode.challenge30Day.week5;

import javax.swing.tree.TreeNode;

public class BinaryTreeMaximumPathSum {
    class TreeNode {
        TreeNode left, right;
        int val;
    }

    int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);

        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        ans = Math.max(ans, Math.max(node.val, Math.max(left + node.val + right, Math.max(left + node.val, right + node.val))));

        return Math.max(Math.max(left + node.val, right + node.val), node.val);
    }
}
