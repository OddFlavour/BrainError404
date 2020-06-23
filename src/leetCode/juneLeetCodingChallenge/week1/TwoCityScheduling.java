package leetCode.juneLeetCodingChallenge.week1;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TwoCityScheduling {
    public int twoCitySchedCost(int[][] costs) {
        int ans = 0;
        int n = costs.length / 2, a = n, b = n;

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // Max heap with value as the absolute difference between the two costs
                return Math.abs(o2[0] - o2[1]) - Math.abs(o1[0] - o1[1]);
            }
        });

        for (int i = 0; i < n * 2; i++) {
            pq.add(costs[i]);
        }

        while (!pq.isEmpty()) {
            int[] target = pq.poll();

            if (target[0] < target[1]) {
                if (a > 0) {
                    ans += target[0];
                    a--;
                } else {
                    ans += target[1];
                    b--;
                }
            } else {
                if (b > 0) {
                    ans += target[1];
                    b--;
                } else {
                    ans += target[0];
                    a--;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][][] tests = {
                {{1, 100}, {250, 250}, {251, 9000}, {251, 250}},
                {{1, 100}, {100, 1}},
                {{259,770},{448,54},{926,667},{184,139},{840,118},{577,469}}
        };

        for (int i = 0; i < tests.length; i++) {
            TwoCityScheduling s = new TwoCityScheduling();
            System.out.println(s.twoCitySchedCost(tests[i]));
        }
    }
}
