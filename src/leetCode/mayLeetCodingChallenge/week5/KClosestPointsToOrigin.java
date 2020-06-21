package leetCode.mayLeetCodingChallenge.week5;

import java.util.*;

public class KClosestPointsToOrigin {
    private int getDistance(int[] point) {
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

    public int[][] kClosestFASTEST(int[][] points, int K) {
        // INTUITION: use basic idea of quick sort

        int n = points.length, l = 0, r = n - 1;

        while (l <= r) {
            int mid = helper(points, r);
            if (mid == K) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return Arrays.copyOfRange(points, 0, K);
    }

    private int helper(int[][] points, int last) {
        // Use Lomuto partition scheme
        // https://upload.wikimedia.org/wikipedia/commons/8/84/Lomuto_animated.gif
        int[] pivot = points[last];

        int i = 0, j = 0;
        while (j < last) {
            while (j < last && getDistance(points[j]) > getDistance(pivot))
                j++;

            int[] temp = points[i];
            points[i] = points[j];
            points[j] = temp;

            i++;
            j++;
        }

        return i;
    }

    public static void main(String[] args) {
        int[][][] testPoints = {
                {{1, 3}, {-2, 2}},
                {{3, 3}, {5, -1}, {-2, 4}},
                {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}},
                {{1, 3}, {-2, 2}, {2, -2}},
                {{2, 2}, {2, 2}, {2, 2}, {2, 2}, {2, 2}, {2, 2}, {1, 1}},
                {{9, 0}, {7, 10}, {-4, -2}, {3, -9}, {9, 1}, {-5, -1}}
        };

        int[] testKs = {
                1,
                2,
                4,
                2,
                1,
                5
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

            answer = s.kClosestFASTEST(testPoints[i], testKs[i]);
            for (int[] p : answer) {
                System.out.printf("[%d, %d], ", p[0], p[1]);
            }
            System.out.println();
        }
    }
}
