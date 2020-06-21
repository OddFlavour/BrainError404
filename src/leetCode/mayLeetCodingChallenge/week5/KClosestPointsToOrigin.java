package leetCode.mayLeetCodingChallenge.week5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {
    private int getDistance(int [] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    public int[][] kClosest(int[][] points, int K) {
        List<int[]> answer = new ArrayList<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] left, int[] right) {
                return getDistance(right) - getDistance(left);
            }
        });

        for (int[] p : points) {
            pq.add(p);

            // Ensure 'pq' does not contain extraneous elements
            if (pq.size() > K) pq.poll();
        }

        while (K > 0) {
            answer.add(pq.poll());
            K--;
        }

        return answer.toArray(new int[answer.size()][]);
    }

    public static void main(String[] args) {
        int[][][] testPoints = {
                {{1, 3}, {-2, 2}},
                {{3, 3}, {5, -1}, {-2, 4}},
                {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}}
        };

        int[] testKs = {
                1,
                2,
                4
        };

        for (int i = 0; i < testPoints.length; i++) {
            KClosestPointsToOrigin s = new KClosestPointsToOrigin();
            long start = System.currentTimeMillis();
            int[][] answer = s.kClosest(testPoints[i], testKs[i]);
            long end = System.currentTimeMillis();

            System.out.println(end - start);

            for (int[] p : answer) {
                System.out.printf("[%d, %d], ", p[0], p[1]);
            }
            System.out.println();
        }
    }
}
