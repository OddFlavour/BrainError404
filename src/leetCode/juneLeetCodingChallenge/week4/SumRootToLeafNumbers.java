package leetCode.juneLeetCodingChallenge.week4;

import leetCode.tools.TreeTools;
import leetCode.tools.TreeTools.TreeNode;

public class SumRootToLeafNumbers {
    /*
    Keeping track of the string takes too long to append and then convert it into integer during final stage
    Instead just keep track of the digits and shift base-10 integer to left as needed.

    For example, if we have a tree as following:
    1 <-- root
    |\
    2 3

    Then we start processing from the root with accumulation = 0.
    Upon processing the root, we need to shift the base-10 integer, so multiply by 10.
    Then we add on the current node's value.

    Why this works? Image if the accumulation = 12 for the following tree:
    1
    |
    2
    |
    3 <-- about to process this

    When we are processing the final node, we'll shift it to the left, so accumulation = 120. Therefore if we add in
    the node's value then we'll get the sum from root to leaf.
     */

    int ans = 0;

    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;

        dfs(root, 0);

        return ans;
    }

    private void dfs(TreeNode root, int accumulation) {
        accumulation *= 10;
        accumulation += root.val;

        if (root.left == null && root.right == null) {
            ans += accumulation;
            return;
        }

        if (root.left != null) {
            dfs(root.left, accumulation);
        }
        if (root.right != null) {
            dfs(root.right, accumulation);
        }
    }

    public static void main(String[] args) {
        Integer[][] tests = {
                {1, 2, 3}
        };

        for (int i = 0; i < tests.length; i++) {
            SumRootToLeafNumbers s = new SumRootToLeafNumbers();
            System.out.println(s.sumNumbers(TreeTools.convertToTree(tests[i])));
        }
    }
}
