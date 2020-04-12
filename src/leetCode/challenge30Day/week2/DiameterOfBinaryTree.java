package leetCode.challenge30Day.week2;

public class DiameterOfBinaryTree {

    public static void main(String[] args) {
        TreeNode nine = new TreeNode(9);
        TreeNode eight = new TreeNode(8);
        TreeNode seven = new TreeNode(7);
        TreeNode six = new TreeNode(6);
        TreeNode five = new TreeNode(5);
        TreeNode four = new TreeNode(4);
        TreeNode three = new TreeNode(3);
        TreeNode two = new TreeNode(2);
        TreeNode one = new TreeNode(2);

        one.left = two;
        one.right = three;

        two.left = four;
        two.right = five;

        four.left = six;

        six.left = eight;

        five.right = seven;

        seven.right = nine;

        TSolution s = new TSolution();
        System.out.println(s.diameterOfBinaryTree(one));
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */

    static class Solution {
        public int diameterOfBinaryTree(TreeNode root) {
            return Math.max(helper(root).maxDiameter - 1, 0);
        }

        private Pair<Integer> helper(TreeNode root) {
            if (root == null) return new Pair<Integer>(0, 0);

            Pair<Integer> lp = helper(root.left);
            Pair<Integer> rp = helper(root.right);

            int rootDiameter = lp.maxBranch + rp.maxBranch + 1;
            int maxDiameter = Math.max(Math.max(lp.maxDiameter, rp.maxDiameter), rootDiameter);

            int maxBranch = Math.max(lp.maxBranch, rp.maxBranch) + 1;

            return new Pair<Integer>(maxBranch, maxDiameter);
        }
    }

    static class TSolution {
        int ans;
        public int diameterOfBinaryTree(TreeNode root) {
            ans = 1;
            depth(root);
            return ans - 1;
        }
        public int depth(TreeNode node) {
            if (node == null) return 0;
            int L = depth(node.left);
            int R = depth(node.right);
            ans = Math.max(ans, L+R+1);
            return Math.max(L, R) + 1;
        }
    }
}

class Pair<T> {

    T maxBranch, maxDiameter;

    public Pair(T left, T right) {
        this.maxBranch = left;
        this.maxDiameter = right;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
