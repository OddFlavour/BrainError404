package ctce;

public class C2P7 {
    static class Solution {
        public LinkedNode intersection(LinkedNode l1, LinkedNode l2) {
            LinkedNode head1 = l1;
            LinkedNode head2 = l2;

            LinkedNode lag1 = null;
            LinkedNode lag2 = null;

            while (l1 != null || l2 != null) {
                if (l1 != null) {
                    LinkedNode temp1 = l1.next;
                    l1.next = lag1;
                    lag1 = l1;
                    l1 = temp1;
                }

                if (l2 != null) {
                    LinkedNode temp2 = l2.next;
                    l2.next = lag2;
                    lag2 = l2;
                    l2 = temp2;
                }
            }

            return null;
        }
    }

    static class LinkedNode {
        int val;
        LinkedNode next;

        public LinkedNode() {}
        public LinkedNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        LinkedNode l = new LinkedNode();

        System.out.println("hello");
    }
}
