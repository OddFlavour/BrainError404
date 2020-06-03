package leetCode.mayLeetCodingChallenge.week3;

public class OnlineStockSpan {
    class StockSpanner {
        class ListNode {
            int value;
            int counts;
            ListNode prev;

            public ListNode(int value) {
                this.value = value;
                this.counts = 1;
            }
        }

        ListNode tail;

        public StockSpanner() {
        }

        public int next(int price) {
            if (tail == null) tail = new ListNode(price);
            else {
                if (tail.value <= price) {
                    tail.value = price;
                    tail.counts++;
                } else {
                    ListNode newTail = new ListNode(price);
                    newTail.prev = tail;
                    tail = newTail;
                }
            }

            return traverse(price);
        }

        private int traverse(int price) {
            int ans = 0;

            ListNode curr = tail;
            while (curr != null && curr.value <= price) {
                ans += curr.counts;
                curr = curr.prev;
            }

            return ans;
        }
    }
}
