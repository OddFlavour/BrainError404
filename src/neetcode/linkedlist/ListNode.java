package neetcode.linkedlist;

class ListNode {
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

    @Override
    public String toString() {
        return String.valueOf(this.val);
    }

    static ListNode fromListOfInt(int[] listOfInt) {
        if (listOfInt.length == 0) {
            return null;
        }

        ListNode head = new ListNode(listOfInt[0]);
        ListNode curr = head;

        for (int i = 1; i < listOfInt.length; i++) {
            ListNode next = new ListNode(listOfInt[i]);

            curr.next = next;
            curr = next;
        }

        return head;
    }
}
