package leetCode.juneLeetCodingChallenge.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        int[][] ans = new int[people.length][];

        // Sort by height
        Arrays.sort(people, Comparator.comparingInt(value -> value[0]));

        for (int i = 0; i < people.length; i++) {
            int counter = people[i][1];

            int curr = 0;
            while (counter > 0) {
                // If it's a empty slot, or the height of the slot is greater or equal
                if (ans[curr] == null || ans[curr][0] >= people[i][0]) {
                    counter--;
                }
                curr++;
            }

            // If we end on a non-empty slot, find the closest empty slot to the right of it
            while (curr < ans.length && ans[curr] != null) {
                curr++;
            }

            // Set the position
            ans[curr] = people[i];
        }

        return ans;
    }

    public int[][] reconstructQueueFastest(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            // Sort from tallest to shortest, if height is same, then sort by least to greatest for o[1]
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o2[0] - o1[0];
            }
        });

        List<int[]> ans = new ArrayList<>();
        for (int[] p : people) {
            ans.add(p[1], p);
        }

        return ans.toArray(new int[ans.size()][]);
    }

    public static void main(String[] args) {
        int[][][] tests = {
                {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}},
                {{7, 1}, {4, 4}, {7, 0}, {5, 2}, {6, 1}, {5, 0}},
                {{1, 0}},
                {},
                {{1, 0}, {2, 0}}
        };

        for (int i = 0; i < tests.length; i++) {
            QueueReconstructionByHeight s = new QueueReconstructionByHeight();
            int[][] answer = s.reconstructQueueFastest(tests[i]);

            for (int[] p : answer) {
                System.out.printf("(%d, %d), ", p[0], p[1]);
            }
            System.out.println();
        }
    }
}
