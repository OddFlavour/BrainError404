package leetCode.challenge30Day.week4;

public class MaximalSquare {
    int m, n;

    public int maximalSquare(char[][] matrix) {
        int maxWidth = 0;

        m = matrix.length;
        n = m > 0 ? matrix[0].length : 0;

        for (int i = 0; i < m - maxWidth; i++) {
            for (int j = 0; j < n - maxWidth; j++) {
                if (matrix[i][j] == '1') {
                    maxWidth = Math.max(maxWidth, isSquare(matrix, i, j));
                }
            }
        }

        return maxWidth * maxWidth;
    }

    private int isSquare(char[][] matrix, int iC, int jC) {
        int width = 0;

        Outer:
        {
            while (true) {
                for (int i = 0; i < width; i++) {
                    // Determine if the lower triangular is same as upper triangular
                    char lower = '0';
                    if (iC + width < m && jC + i < n) {
                        lower = matrix[iC + width][jC + i];
                    }

                    char upper = '0';
                    if (iC + i < m && jC + width < n) {
                        upper = matrix[iC + i][jC + width];
                    }

                    boolean isEqual = lower == '1' && upper == '1';
                    if (!isEqual) {
                        break Outer;
                    }
                }

                if (matrix[iC + width][jC + width] == '1') {
                    width++;
                } else {
                    break Outer;
                }
            }
        }

        return width;
    }
}
