package leetCode.juneLeetCodingChallenge.week3;

public class PermutationSequence {
    class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * Solution idea, we don't really care about the other permutations since they don't affect our answer.
     *
     * In order to find the permutation we want, we'll base off of the following:
     * Let n = n in the problem, k' = number of permutations that has occurred/accounted for (intial value = k - 1)
     * - The i-th (i=1...n) index only changes if there has occurred (n - i)! permutations (a.k.a (n-i)! <= k')
     *      - For example: n = 3, k = 3, "213" | The 1-th index only changed because there has occurred (3 - 1)! = 2! = 2 permutations <= k' = k - 1 = 2
     *      - Using the same example, the 2-th index could not have changed because (3-2)!=1!=1, while k' = 0
     *
     * Then we'll also need to record what numbers we have not used, so we store it in a linked list, removing the used ones.
     * The linked list works because once a number has been used, the permutations that are created without that number, will respect
     * numerical order.
     * - For example: say n=4, we take out '2', then the permutations will be 134, 143, 314, 341, 413, 431
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        k = k - 1;

        int nFactorial = 1;
        Node head = new Node(-1); // dummy head

        Node curr = head;
        for (int i = 1; i <= n; i++) {
            nFactorial *= i;

            curr.next = new Node(i);
            curr = curr.next;
        }

        StringBuilder sb = new StringBuilder();

        int count = n;
        while (sb.length() < n) {
            curr = head;
            nFactorial /= count--;

            int index = k / nFactorial;
            k = k % nFactorial;

            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }

            // Add on the candidate
            sb.append(curr.next.value);

            // Take out the candidate
            Node temp = curr.next.next;
            curr.next.next = null;
            curr.next = temp;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int[] testNs = {
                3
        };

        int[] testKs = {
                4
        };

        for (int i = 0; i < testNs.length; i++) {
            PermutationSequence s = new PermutationSequence();
//            System.out.println(s.getPermutation(testNs[i], testKs[i]));
            for (int x = 0; x < 1; x++) {
                System.out.println(s.getPermutation(1, x + 1));
            }
        }
    }
}
