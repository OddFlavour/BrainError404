package leetCode.challenge30Day.week3;

public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int ans = 0;

        if (grid.length == 0) {
            return ans;
        }

        boolean[][] seen = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !seen[i][j]) {
                    dfs(grid, seen, i, j);
                    ans++;
                }
            }
        }

        return ans;
    }

    public void dfs(char[][] grid, boolean[][] seen, int i, int j) {
        // 4 cases
        int[][] cases = {
                {i - 1 < 0 ? 0 : i - 1, j},
                {i + 1 > seen.length - 1 ? seen.length - 1 : i + 1, j},
                {i, j - 1 < 0 ? 0 : j - 1},
                {i, j + 1 > seen[0].length - 1 ? seen[0].length - 1 : j + 1}
        };

        for (int c = 0; c < cases.length; c++) {
            int a = cases[c][0];
            int b = cases[c][1];
            if (grid[a][b] == '1' && !seen[a][b]) {
                seen[a][b] = true;
                dfs(grid, seen, a, b);
            }
        }
    }
}
