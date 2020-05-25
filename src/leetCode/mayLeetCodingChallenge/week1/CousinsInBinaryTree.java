package leetCode.mayLeetCodingChallenge.week1;

import leetCode.tools.TreeTools;

import java.util.HashSet;
import java.util.Set;

import static leetCode.tools.TreeTools.TreeNode;

public class CousinsInBinaryTree {

    Set<TreeNode> parents = new HashSet<>();
    Set<Integer> depths = new HashSet<>();

    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, 0, x, y);

        return parents.size() == 2 && depths.size() == 1;
    }

    private void dfs(TreeNode node, int depth, int x, int y) {
        if (node.left != null) {
            if (node.left.val == x || node.left.val == y) {
                parents.add(node);
                depths.add(depth + 1);
            }

            dfs(node.left, depth + 1, x, y);
        }

        if (node.right != null) {
            if (node.right.val == x || node.right.val == y) {
                parents.add(node);
                depths.add(depth + 1);
            }

            dfs(node.right, depth + 1, x, y);
        }
    }

    public static void main(String[] args) {
        Integer[][] tests = {
                {1, 2, 3, 4, null, null, null},
                {1, 2, 3, null, 4, null, 5},
                {1, 2, 3, null, 4, null, null},
                {1, 2, null}
        };

        Integer[][] cousins = {
                {4, 3},
                {5, 4},
                {2, 3},
                {1, 3}
        };

        for (int i = 0; i < tests.length; i++) {
            TreeTools.TreeNode a = TreeTools.convertToTree(tests[i]);

            CousinsInBinaryTree s = new CousinsInBinaryTree();
            System.out.println(s.isCousins(a, cousins[i][0], cousins[i][1]));
        }
    }
}
