package leetCode.mayLeetCodingChallenge.week5;

import java.util.*;

public class CourseSchedule {
    int N;

    /**
     * Runs in about 10ms on LC
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        N = numCourses;

        boolean[] visited = new boolean[N];

        Map<Integer, Set<Integer>> paths = new HashMap<>();

        // Transpose
        boolean[] visitedT = new boolean[N];

        Map<Integer, Set<Integer>> pathsT = new HashMap<>();

        // Build paths
        for (int[] path : prerequisites) {
            int a = path[0], b = path[1];

            if (a == b) return false;

            if (!paths.containsKey(a)) paths.put(a, new HashSet<>());
            paths.get(a).add(b);

            if (!pathsT.containsKey(b)) pathsT.put(b, new HashSet<>());
            pathsT.get(b).add(a);
        }

        /*
         INTUITION: If there are any strongly connected components of size greater than 1, then result is false
         */

        // Find strongly connected components
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            dfs(s, paths, visited, i);
        }

        while (!s.isEmpty()) {
            int focus = s.pop();

            Stack<Integer> scc = new Stack<>();
            dfs(scc, pathsT, visitedT, focus);

            if (scc.size() >= 2) return false;
        }

        return true;
    }

    private void dfs(Stack<Integer> s, Map<Integer, Set<Integer>> paths, boolean[] visited, int start) {
        if (visited[start]) return;

        visited[start] = true;

        if (paths.containsKey(start)) {
            for (int i : paths.get(start)) {
                dfs(s, paths, visited, i);
            }
        }

        s.add(start);
    }

    /**
     * Runs in about 5ms on LC
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        N = numCourses;

        boolean[] visited = new boolean[N];
        boolean[] recStack = new boolean[N];

        Map<Integer, Set<Integer>> paths = new HashMap<>();

        // Build paths
        for (int[] path : prerequisites) {
            int a = path[0], b = path[1];

            if (a == b) return false;

            if (!paths.containsKey(a)) paths.put(a, new HashSet<>());
            paths.get(a).add(b);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!dfs2(paths, visited, recStack, i)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs2(Map<Integer, Set<Integer>> paths, boolean[] visited, boolean[] recStack, int start) {
        if (recStack[start]) return false;

        if (visited[start]) return true;

        visited[start] = true;
        recStack[start] = true;

        if (paths.containsKey(start)) {
            for (int i : paths.get(start)) {
                if (!dfs2(paths, visited, recStack, i)) {
                    return false;
                }
            }
        }

        recStack[start] = false;

        return true;
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
            System.out.println(
                    s.canFinish(testNs[i], testPrereqs[i])
                            == s.canFinish2(testNs[i], testPrereqs[i])
            );
        }
    }
}
