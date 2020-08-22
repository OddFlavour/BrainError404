package leetCode.julyLeetCodingChallenge.week1;

import leetCode.tools.TreeTools.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        // BFS
        Queue<TreeNode> q = new LinkedList<>();

        if (root != null) {
            q.add(root);
        }

        while (!q.isEmpty()) {
            List<Integer> level = new ArrayList<>();

            int n = q.size();
            for (int i = 0; i < n; i++) {
                TreeNode curr = q.poll();

                if (curr.left != null) q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);

                level.add(curr.val);
            }

            // Add on to the list
            // Alternatively, can use recursion, so that we don't need to insert at index 0
            ans.add(0, level);
        }

        return ans;
    }
}
