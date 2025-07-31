package neetcode.linkedlist;

public class ReorderLinkedList {
    static class Solution {
        private ListNode head;
        private int count;

        public void reorderList(ListNode head) {
            this.head = head;

            helper(head);
        }

        private ListNode helper(ListNode curr) {
            if (curr == null) {
                return this.head;
            }

            count++;
            ListNode result = helper(curr.next);
            ListNode newFront = result.next;

            if (count > 0) {
                result.next = curr;
                curr.next = newFront;
                count -= 2;
            }

            return newFront;
        }
    }

    public static void main(String[] args) {
        ListNode[] ts = {
                ListNode.fromListOfInt(new int[]{2, 4, 6, 8})
        };

        for (ListNode t : ts) {
            Solution s = new Solution();
            s.reorderList(t);
            System.out.println(t);
        }
    }
}
