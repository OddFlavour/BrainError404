package leetCode.mayLeetCodingChallenge.week4;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int indexA = 0, indexB = 0;

        List<int[]> intersections = new ArrayList<>();

        while (indexA < A.length && indexB < B.length) {
            // Determine intersection between current pair
            int a = Math.max(A[indexA][0], B[indexB][0]);
            int b = Math.min(A[indexA][1], B[indexB][1]);

            // Verify the intersection is valid
            if (a <= b) {
                // Add the intersection
                intersections.add(new int[]{a, b});
            }

            // Determine which index to increment
            if (A[indexA][1] < B[indexB][1])
                indexA++;
            else
                indexB++;
        }

        return intersections.toArray(new int[intersections.size()][]);
    }
}
