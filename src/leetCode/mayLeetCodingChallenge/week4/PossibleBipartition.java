package leetCode.mayLeetCodingChallenge.week4;

import java.util.*;

public class PossibleBipartition {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer, Set<Integer>> g = new HashMap<>();

        for (int[] dislike : dislikes) {
            // According to constraints, 'dislike[0] < dislike[1]'
            int a = dislike[0], b = dislike[1];

            if (!g.containsKey(a))
                g.put(a, new HashSet<>());

            if (a != b)
                g.get(a).add(b);
        }

        for (int start : g.keySet()) {
            if (!dfs(g, start, new boolean[N + 1]))
                return false;
        }

        return true;
    }

    private boolean dfs(Map<Integer, Set<Integer>> g, int start, boolean[] seen) {
        // Determine if the cycle created is within +-1 depth of each other
        if (seen[start])
            return false;

        // Set current point as seen
        seen[start] = true;

        if (g.containsKey(start)) {
            // Go through each path from the start
            for (int pathEnd : g.get(start)) {
                // Return early if we found a cycle
                if (!dfs(g, pathEnd, seen))
                    return false;
            }
        }

        // Default value indicating bipartition is possible
        return true;
    }

    public static void main(String[] args) {
        int[] testNs = {
                3,
                3,
                4,
                3,
                5,
                2,
                1,
                10,
                4
        };
        int[][][] testDislikes = {
                {{1, 2}, {2, 3}, {1, 3}},
                {{1, 2}, {2, 3}},
                {{1, 2}, {1, 3}, {2, 4}},
                {{1, 2}, {1, 3}, {2, 3}},
                {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}},
                {{1, 2}},
                {{1, 1}},
                {{4, 7}, {4, 8}, {5, 6}, {1, 6}, {3, 7}, {2, 5}, {5, 8}, {1, 2}, {4, 9}, {6, 10}, {8, 10}, {3, 6}, {2, 10}, {9, 10}, {3, 9}, {2, 3}, {1, 9}, {4, 6}, {5, 7}, {3, 8}, {1, 8}, {1, 7}, {2, 4}},
                {{1, 2}, {2, 3}, {3, 4}, {1, 4}},
        };

        boolean[] answers = {
                false,
                true,
                true,
                false,
                false,
                true,
                true,
                true,
                true,
                true
        };

        for (int i = 0; i < testNs.length; i++) {
            PossibleBipartition s = new PossibleBipartition();
            System.out.println(s.possibleBipartition(testNs[i], testDislikes[i]) == answers[i]);
        }
    }
}
