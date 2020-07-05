package leetCode.juneLeetCodingChallenge.week3;

import java.util.ArrayList;
import java.util.List;

public class SurroundedRegions {
    char o = 'o', x = 'x', z = 'z';
    boolean onBorder = false;

    int m, n;

    // Better solution would be to just start dfs from any 'o' on border
    public void solve(char[][] board) {
        m = board.length;
        n = m > 0 ? board[0].length : 0;

        // Excluding border
        for (int row = 1; row < m - 1; row++) {
            for (int col = 1; col < n - 1; col++) {
                if (board[row][col] == o) {
                    dfs(board, row, col);
                    for (int row1 = 0; row1 < m; row1++) {
                        for (int col1 = 0; col1 < n; col1++) {
                            if (board[row1][col1] == '1')
                                board[row1][col1] = onBorder ? z : x;
                        }
                    }
                    onBorder = false;
                }
            }
        }

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == z)
                    board[row][col] = o;
            }
        }
    }

    private void dfs(char[][] board, int row, int col) {
        if (board[row][col] == o) {
            if (row == 0 || col == 0 || row == m - 1 || col == n - 1)
                onBorder = true;

            // Set as visited
            board[row][col] = '1';

            if (isValid(row - 1, col))
                dfs(board, row - 1, col);
            if (isValid(row + 1, col))
                dfs(board, row + 1, col);
            if (isValid(row, col - 1))
                dfs(board, row, col - 1);
            if (isValid(row, col + 1))
                dfs(board, row, col + 1);
        }
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < m
                && col >= 0 && col < n;
    }

    public static void main(String[] args) {
        String[] tests = {
                "xxxx\n" +
                        "xoox\n" +
                        "xxox\n" +
                        "xoxx",
                "xxoo\n" +
                        "xoox\n" +
                        "xoox\n" +
                        "xxxx",
                "xxxoo\n" +
                        "xoxox\n" +
                        "xxoox\n" +
                        "xxxxx",
                "",
                "ooooo",
                "xxoox",
                "xxx\n" +
                        "xox\n" +
                        "xxx",
                "xxxoo\n" +
                        "xxoox\n" +
                        "xoxox\n" +
                        "xxxxx",
                "xxxxox\n" +
                        "oxxoox\n" +
                        "xoxooo\n" +
                        "xoooxo\n" +
                        "ooxxox\n" +
                        "xoxoxx"
        };

        for (int i = 0; i < tests.length; i++) {
            String[] tokens = tests[i].split("\\n");
            List<char[]> temp = new ArrayList<>();
            for (String token : tokens) {
                temp.add(token.toCharArray());
            }

            char[][] test = temp.toArray(new char[temp.size()][]);

            SurroundedRegions s = new SurroundedRegions();
            s.solve(test);

            printBoard(test);
        }
    }

    private static void printBoard(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                System.out.printf("%c", board[row][col]);
            }
            System.out.println();
        }
        System.out.println("====");
    }
}
