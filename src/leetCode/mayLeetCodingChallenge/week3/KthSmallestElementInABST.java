package leetCode.mayLeetCodingChallenge.week3;

public class KthSmallestElementInABST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

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

    int threshold = 0;
    int ans = 0;

    public int kthSmallest(TreeNode root, int k) {
        threshold = k;
        dfs(root);

        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            threshold--;
        } else {
            dfs(root.left);

            // Curr
            if (threshold == 0) ans = root.val;

            dfs(root.right);
        }
    }
}
