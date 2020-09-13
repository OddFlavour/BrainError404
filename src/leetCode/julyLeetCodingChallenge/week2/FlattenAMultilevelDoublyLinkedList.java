package leetCode.julyLeetCodingChallenge.week2;

public class FlattenAMultilevelDoublyLinkedList {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

    public Node flatten(Node head) {
         flattenHelper(head);

        return head;
    }

    private Node[] flattenHelper(Node head) {
        Node[] pair = new Node[2];

        Node lastCurr = null;
        Node curr = head;
        // Traverse the linked list until an element has a child
        while (curr != null) {
            // Recursively call 'flatten' on the child to get back the head of the flattened child linked list
            if (curr.child != null) {
                Node[] next = flattenHelper(curr.child);
                curr.child = null;

                // Fix the tail of the flattened child list
                fixPointers(next[1], curr.next);

                // Fix the head of the flattened child list
                fixPointers(curr, next[0]);

                // Set the tail of the flattened child list as the next element to improve running time
                curr = next[1];
            }

            lastCurr = curr;
            curr = curr.next;
        }

        pair[0] = head;
        pair[1] = lastCurr;

        return pair;
    }

    private void fixPointers(Node curr, Node next) {
        curr.next = next;
        if (next != null) {
            next.prev = curr;
        }
    }

    public static void main(String[] args) {
        FlattenAMultilevelDoublyLinkedList s = new FlattenAMultilevelDoublyLinkedList();
        Node ans = s.flatten(null);

        System.out.println(ans);
    }
}
