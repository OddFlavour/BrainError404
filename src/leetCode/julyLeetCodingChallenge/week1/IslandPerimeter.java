package leetCode.julyLeetCodingChallenge.week1;

public class IslandPerimeter {
    int ans = 0;
    int[][] grid;

    public int islandPerimeter(int[][] grid) {
        this.grid = grid;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(i, j);

                    // Early return because there is only one island
                    return ans;
                }
            }
        }

        return ans;
    }

    private void dfs(int row, int col) {
        grid[row][col] = 2;

        if (isValidCoordinate(row - 1, col)) {
            if (grid[row - 1][col] == 1) {
                dfs(row - 1, col);
            } else if (grid[row - 1][col] == 0) {
                ans += 1;
            }
        } else {
            ans += 1;
        }
        if (isValidCoordinate(row, col - 1)) {
            if (grid[row][col - 1] == 1) {
                dfs(row, col - 1);
            } else if (grid[row][col - 1] == 0) {
                ans += 1;
            }
        } else {
            ans += 1;
        }
        if (isValidCoordinate(row + 1, col)) {
            if (grid[row + 1][col] == 1) {
                dfs(row + 1, col);
            } else if (grid[row + 1][col] == 0) {
                ans += 1;
            }
        } else {
            ans += 1;
        }
        if (isValidCoordinate(row, col + 1)) {
            if (grid[row][col + 1] == 1) {
                dfs(row, col + 1);
            } else if (grid[row][col + 1] == 0) {
                ans += 1;
            }
        } else {
            ans += 1;
        }
    }

    private boolean isValidCoordinate(int r, int c) {
        return r >= 0 && r < grid.length
                && c >= 0 && c < grid[0].length;
    }

    public static void main(String[] args) {
        int[][][] tests = {
//                {{0, 1, 0, 0},
//                        {1, 1, 1, 0},
//                        {0, 1, 0, 0},
//                        {1, 1, 0, 0}},
                {{1, 1}}
        };

        for (int i = 0; i < tests.length; i++) {
            IslandPerimeter s = new IslandPerimeter();
            System.out.println(s.islandPerimeter(tests[i]));
        }
    }
}
