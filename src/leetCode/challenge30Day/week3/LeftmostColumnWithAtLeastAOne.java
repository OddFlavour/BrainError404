package leetCode.challenge30Day.week3;

import java.util.List;

public class LeftmostColumnWithAtLeastAOne {
    interface BinaryMatrix {
        public int get(int row, int col);
        public List<Integer> dimensions();
    }

    int ans = Integer.MAX_VALUE;
    int rows = 0;
    int cols = 0;

    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        rows = dimensions.get(0);
        cols = dimensions.get(1);

        for (int i = 0; i < rows; i++) {
            binSearch(binaryMatrix, i);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public void binSearch(BinaryMatrix matrix, int row) {
        int left = 0, right = cols;  // not 'cols - 1' because of last line to detect no zeroes on row
        while (left < right) {
            int mid = (left + right) / 2;

            if (matrix.get(row, mid) == 1) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        if (left < cols) ans = left < ans ? left : ans;
    }
}
