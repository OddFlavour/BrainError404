package neetcode.binarysearch;

public class SeachA2DMatrix {
    static class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length;
            int n = matrix[0].length;

            int left = 0, right = m * n - 1;

            while (left < right) {
                int curr = left / 2 + right / 2;
                int currValue = matrix[getM(curr, n)][getN(curr, n)];

                if (currValue < target) {
                    left = curr + 1;
                } else if (currValue > target) {
                    right = curr - 1;
                } else {
                    return true;
                }
            }

            return matrix[getM(left, n)][getN(left, n)] == target;
        }

        private int getM(int curr, int n) {
            return curr / n;
        }

        private int getN(int curr, int n) {
            return curr % n;
        }
    }

    public static void main(String[] args) {
        int[][][] ts1 = {
                {{1,2,4,8}, {10,11,12,13}, {14,20,30,40}},
                {{1,3,5,7},{10,11,16,20},{23,30,34,60}}
        };

        int[] ts2 = {
                15,
                3
        };

        for (int i = 0; i < ts1.length; i++) {
            Solution s = new Solution();
            System.out.println(s.searchMatrix(ts1[i], ts2[i]));
        }
    }
}
