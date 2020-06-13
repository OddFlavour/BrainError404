package leetCode.random;

public class MergeTwoSortedLists {
    public static class ListNode {
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

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode curr = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                curr.next = new ListNode(l2.val);
                l2 = l2.next;
            }

            curr = curr.next;
        }

        if (l1 != null) {
            curr.next = l1;
        }

        if (l2 != null) {
            curr.next = l2;
        }

        return head.next;
    }

    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwoListsRecursive(l1.next, l2);
            return l1;
        } else{
            l2.next = mergeTwoListsRecursive(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        int[][] testAs = {
                {1, 2, 4},
                {1, 2, 3},
        };

        int[][] testBs = {
                {1, 3, 4},
                {4, 5, 6}
        };

        for (int i = 0; i < testAs.length; i++) {
            MergeTwoSortedLists s = new MergeTwoSortedLists();
            ListNode ans = s.mergeTwoLists(toListNode(testAs[i]), toListNode(testBs[i]));
            System.out.println();
        }
    }

    private static ListNode toListNode(int[] numbers) {
        ListNode head = new ListNode();
        ListNode curr = head;

        for (int number : numbers) {
            curr.next = new ListNode(number);
            curr = curr.next;
        }

        return head.next;
    }
}
