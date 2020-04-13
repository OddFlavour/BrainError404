package leetCode.challenge30Day.week2;

import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < stones.length; i++) {
            pq.add(stones[i]);
        }

        // Smashing
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();

            int result = a - b;
            if (result > 0) {
                pq.add(result);
            }
        }

        return pq.isEmpty() ? 0 : pq.poll();
    }

    public static void main(String[] args) {
        int[] t1 = { 2,7,4,1,8,1 };
        int[] t2 = { 1, 1, 1, 1 };
        int[] t3 = { 1, 1, 3, 4, 1 };
        int[] t4 = { 1 };

        LastStoneWeight s = new LastStoneWeight();
        System.out.println(s.lastStoneWeight(t1));
        System.out.println(s.lastStoneWeight(t2));
        System.out.println(s.lastStoneWeight(t3));
        System.out.println(s.lastStoneWeight(t4));
    }
}
