package leetCode.juneLeetCodingChallenge.week1;

import leetCode.tools.TreeTools;

public class InvertBinaryTree {
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

    public TreeNode invertTree(TreeNode root) {
        rec(root);

        return root;
    }

    private void rec(TreeNode root) {
        if (root == null) return;

        rec(root.left);
        rec(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    public static void main(String[] args) {
        Integer[][] tests = {
                {4, 2, 7, 1, 3, 6, 9},
        };

        for (int i = 0; i < tests.length; i++) {
            InvertBinaryTree s = new InvertBinaryTree();

            TreeTools.TreeNode test = TreeTools.convertToTree(tests[i]);
            TreeTools.printInorder(test);
        }
    }
}
