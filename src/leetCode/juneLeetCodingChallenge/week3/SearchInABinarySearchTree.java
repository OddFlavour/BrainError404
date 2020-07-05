package leetCode.juneLeetCodingChallenge.week3;

import leetCode.tools.TreeTools;
import leetCode.tools.TreeTools.TreeNode;

public class SearchInABinarySearchTree {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode curr = root;
        while (curr != null && curr.val != val) {
            if (val < curr.val) curr = curr.left;
            else curr = curr.right;
        }

        return curr;
    }

    public TreeNode searchBSTRecursion(TreeNode root, int val) {
        if (root == null || root.val == val) return root;

        if (val < root.val) return searchBSTRecursion(root.left, val);
        else return searchBSTRecursion(root.right, val);
    }

    public static void main(String[] args) {
        Integer[][] testTrees = {
                {4, 2, 7, 1, 3, null, null},
                {4, 2, 7, 1, 3, null, null},
                {62, 2, 93, null, 30, null, null, 15, null, null, null}
        };

        int[] testVals = {
                2,
                5,
                15
        };

        for (int i = 0; i < testTrees.length; i++) {
            SearchInABinarySearchTree s = new SearchInABinarySearchTree();
            TreeNode ans = s.searchBST(TreeTools.convertToTree(testTrees[i]), testVals[i]);
            System.out.println();
        }
    }
}
