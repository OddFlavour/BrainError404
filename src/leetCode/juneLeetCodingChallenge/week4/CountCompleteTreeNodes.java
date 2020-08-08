package leetCode.juneLeetCodingChallenge.week4;

import leetCode.tools.TreeTools;
import leetCode.tools.TreeTools.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class CountCompleteTreeNodes {

    public int countNodesBigN(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        return 1 + dfs(root.left) + dfs(root.right);
    }

    // Time complexity: O(logn * logn)
    // Concept: using binary search, we identify whether the middle index is good or not (depending if it has the same height as the left-most node)
    //          if it's good, then left subtree is perfect, consider only right subtree (however keep in mind that the answer could be that current middle index)
    //          if it's not good, then left subtree is not perfect, consider only left subtree
    //
    // Searching for the correct index: logn
    // Traversing down the tree: logn
    public int countNodes(TreeNode root) {
        // Edge case
        if (root == null) return 0;

        // Normal cases
        TreeNode curr = root;

        int left = 0;
        while (curr != null) {
            left++;
            curr = curr.left;
        }

        curr = root;

        int right = 0;
        while (curr != null) {
            right++;
            curr = curr.right;
        }

        // Edge case
        if (left == right) return ((int) Math.pow(2, left)) - 1;

        curr = root;
        int currDepth = 1;
        int leftIndex = 0, rightIndex = ((int) Math.pow(2, right)) - 1;
        int ansIndex = 0;

        while (curr != null) {
            int depth = mid(curr) + currDepth;
            int midIndex = (leftIndex + rightIndex) / 2;

            if (depth == left) {
                // Final node in tree could either be in current
                // or exists in the right tree
                curr = curr.right;
                ansIndex = midIndex;
                leftIndex = midIndex + 1;
            } else if (depth == right) {
                // Final node in tree exists in left tree
                curr = curr.left;
                rightIndex = midIndex;
            }

            currDepth++;
        }

        return ((int) Math.pow(2, right)) + ansIndex;
    }

    private int mid(TreeNode root) {
        if (root.left != null) return toRightLeaf(root.left, 1);
        else return 0;
    }

    private int toRightLeaf(TreeNode root, int depth) {
        if (root.right != null) return toRightLeaf(root.right, depth + 1);
        else return depth;
    }

    public static void main(String[] args) {
//        Integer[][] tests = {
////                {1, 2, null},
////                {1, 2, 3},
//                {1, 2, 3, 4, 5, null, null}
//        };
//
//        for (int i = 0; i < tests.length; i++) {
//            CountCompleteTreeNodes s = new CountCompleteTreeNodes();
//            System.out.println(s.countNodes(TreeTools.convertToTree(tests[i])));
//        }

        int power = 2;
        while (power < (int) Math.pow(2, 24)) {
            int n = power - 1;

            int i = n;
//            for (int i = 0; i < n; i++) {
                List<Integer> test = new ArrayList<>();

                // Add nodes
                for (int node = 0; node < i; node++) {
                    test.add(0);
                }

                // Add nulls (only if there is a root node)
                if (test.size() > 0) {
                    for (int _null = 0; _null < (n - i); _null++) {
                        test.add(null);
                    }
                }

                // Perform test
                TreeNode testTree = TreeTools.convertToTree(test.toArray(new Integer[test.size()]));

                CountCompleteTreeNodes s = new CountCompleteTreeNodes();
//                System.out.printf("Expected: %d | Result #1: %d | Result #2: %d\n", i, s.countNodes(testTree), s.countNodesBigN(testTree));

                // Speed test
                long start1 = System.currentTimeMillis();
                s.countNodes(testTree);
                long end1 = System.currentTimeMillis();

                long start2 = System.currentTimeMillis();
                s.countNodesBigN(testTree);
                long end2 = System.currentTimeMillis();

                System.out.printf("%d %d\n", end1-start1, end2-start2);
//            }

            power *= 2;
        }
    }
}
