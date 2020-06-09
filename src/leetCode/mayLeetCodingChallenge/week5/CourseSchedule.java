package leetCode.mayLeetCodingChallenge.week5;

import java.util.*;

public class CourseSchedule {
    class Graph {
        boolean[] visited;
        Map<Integer, Set<Integer>> paths;

        public Graph(int N) {
            visited = new boolean[N];
            paths = new HashMap<>();
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph g = new Graph(numCourses);
        Graph gT = new Graph(numCourses);

        // Build paths
        for (int[] path : prerequisites) {
            int a = path[0], b = path[1];

            if (a == b) return false;

            if (!g.paths.containsKey(a)) g.paths.put(a, new HashSet<>());
            g.paths.get(a).add(b);

            if (!gT.paths.containsKey(b)) gT.paths.put(b, new HashSet<>());
            gT.paths.get(b).add(a);
        }

        /*
         INTUITION: If there are any strongly connected components of size greater than 1, then result is false
         */

        // Find strongly connected components
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            dfs(s, g, i);
        }

        while (!s.isEmpty()) {
            int focus = s.pop();

            Stack<Integer> scc = new Stack<>();
            dfs(scc, gT, focus);

            if (scc.size() >= 2) return false;
        }

        return true;
    }

    private void dfs(Stack<Integer> s, Graph g, int start) {
        if (g.visited[start]) return;

        g.visited[start] = true;

        if (g.paths.containsKey(start)) {
            for (int end : g.paths.get(start)) {
                dfs(s, g, end);
            }
        }

        s.add(start);
    }

    public static void main(String[] args) {
        int[] testNs = {
                5,
                2,
                2,
                1,
                1,
                4
        };

        int[][][] testPrereqs = {
                {{1, 0}, {0, 2}, {2, 1}, {0, 3}, {3, 4}},
                {{1, 0}},
                {{1, 0}, {0, 1}},
                {{0, 0}},
                {},
                {{0, 1}, {1, 2}, {2, 3}}
        };

        for (int i = 0; i < testNs.length; i++) {
            CourseSchedule s = new CourseSchedule();
            System.out.println(s.canFinish(testNs[i], testPrereqs[i]));

            long start = System.currentTimeMillis();
            s.canFinish(testNs[i], testPrereqs[i]);
            long end = System.currentTimeMillis();

            System.out.println(end - start);
        }
    }
}
