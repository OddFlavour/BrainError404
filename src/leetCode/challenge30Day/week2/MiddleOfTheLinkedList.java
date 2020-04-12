package leetCode.challenge30Day.week2;

public class MiddleOfTheLinkedList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return val + "," + next;
        }
    }

    public ListNode middleNode(ListNode head) {
        int length = 0;

        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            length++;
        }

        length /= 2;
        curr = head;
        while (length > 0) {
            curr = curr.next;
            length--;
        }

        return curr;
    }

    public ListNode bestSoln(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next !=null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        MiddleOfTheLinkedList s = new MiddleOfTheLinkedList();

        // Tests
        ListNode t1 = s.constructListNode("1,2,3,4,5");
        ListNode t2 = s.constructListNode("1,2,3,4,5,6");

        System.out.println(s.middleNode(t1));
        System.out.println(s.middleNode(t2));
    }

    public ListNode constructListNode(String list) {
        String[] listArray = list.split(",");

        ListNode head = new ListNode(Integer.parseInt(listArray[0]));
        ListNode curr = head;
        for (int i = 1; i < listArray.length; i++) {
            curr.next = new ListNode(Integer.parseInt(listArray[i]));
            curr = curr.next;
        }

        return head;
    }
}
