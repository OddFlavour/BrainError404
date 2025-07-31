package neetcode.linkedlist;

public class RemoveNodeFromEndOfLinkedList {
    static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode turtle = head;
            ListNode hare = head;

            for (int i = 0; i < n; i++) {
                hare = hare.next;
            }

            while (hare != null && hare.next != null) {
                turtle = turtle.next;
                hare = hare.next;
            }

            // If 'hare' is null, then we've reached the end, and hence we want to remove the head
            if (hare == null) {
                head = head.next;
            } else {
                turtle.next = turtle.next.next;
            }

            return head;
        }
    }
}

