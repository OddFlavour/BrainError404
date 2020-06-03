package leetCode.mayLeetCodingChallenge.week3;

public class OddEvenLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode oddEvenList(ListNode head) {
        ListNode oddCurr = head;
        if (oddCurr == null) return head;

        ListNode evenHead = head.next;
        ListNode evenCurr = head.next;
        if (evenCurr == null) return head;

        while (oddCurr.next != null) {
            oddCurr.next = oddCurr.next.next;
            if (oddCurr.next != null) {
                oddCurr = oddCurr.next;
            }

            if (evenCurr.next != null) {
                evenCurr.next = evenCurr.next.next;
                evenCurr = evenCurr.next;
            }
        }

        oddCurr.next = evenHead;

        return head;
    }
}
