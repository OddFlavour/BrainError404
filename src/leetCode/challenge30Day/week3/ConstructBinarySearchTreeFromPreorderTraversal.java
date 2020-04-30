package leetCode.challenge30Day.week3;

import javax.swing.tree.TreeNode;

public class ConstructBinarySearchTreeFromPreorderTraversal {

    class TreeNode {
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

    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode min = new TreeNode(Integer.MIN_VALUE);
        TreeNode max = new TreeNode(Integer.MAX_VALUE);

        int[] indexPointer = {0};

        return rec(preorder, indexPointer, min, max);
    }

    public TreeNode rec(int[] list, int[] indexPointer, TreeNode leftBound, TreeNode rightBound) {
        int val = list[indexPointer[0]++];
        if (val < leftBound.val || val > rightBound.val) {
            indexPointer[0]--;
            return null;
        }

        TreeNode root = new TreeNode(val);

        if (indexPointer[0] < list.length) {
            if (list[indexPointer[0]] < root.val) {
                root.left = rec(list, indexPointer, leftBound, root);
            }
        }

        if (indexPointer[0] < list.length) {
            if (list[indexPointer[0]] > root.val) {
                root.right = rec(list, indexPointer, root, rightBound);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        int[][] tests = {
                {8, 5, 1, 7, 10, 12}
        };

        for (int[] test : tests) {
            ConstructBinarySearchTreeFromPreorderTraversal s = new ConstructBinarySearchTreeFromPreorderTraversal();
            TreeNode temp = s.bstFromPreorder(test);
            System.out.println();
        }
    }
}
