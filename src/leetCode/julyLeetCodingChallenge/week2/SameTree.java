package leetCode.julyLeetCodingChallenge.week2;

import leetCode.tools.TreeTools.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // BFS through each level, if at any point, it is unequal, return false
        Queue<TreeNode> pList = new LinkedList<>();
        Queue<TreeNode> qList = new LinkedList<>();

        pList.offer(p);
        qList.offer(q);

        while (!pList.isEmpty() && !qList.isEmpty()) {
            int size = pList.size();

            for (int i = 0; i < size; i++) {
                TreeNode pNode = pList.poll();
                TreeNode qNode = qList.poll();

                if (pNode == null && qNode == null) continue;
                else if (pNode == null || qNode == null) return false;

                if (pNode.val != qNode.val) {
                    return false;
                }

                pList.add(pNode.left);
                pList.add(pNode.right);

                qList.add(qNode.left);
                qList.add(qNode.right);
            }
        }

        return true;
    }
}
