package neetcode.binarysearch;

public class MedianOfTwoSortedArrays {
    static class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int[] A = nums1;
            int[] B = nums2;
            int total = A.length + B.length;
            int half = total / 2;

            if (B.length < A.length) {
                int[] temp = A;
                A = B;
                B = temp;
            }

            int l = 0, r = A.length - 1;
            while (true) {
                int i = (l + r) / 2;
                int j = half - i - 2;

                int Aleft = (i >= 0) ? A[i] : Integer.MIN_VALUE;
                int Aright = (i + 1 < A.length) ? A[i + 1] : Integer.MAX_VALUE;
                int Bleft = (j >= 0) ? B[j] : Integer.MIN_VALUE;
                int Bright = (j + 1 < B.length) ? B[j + 1] : Integer.MAX_VALUE;

                if (Aleft <= Bright && Bleft <= Aright) {
                    if (total % 2 == 1) {
                        return Math.min(Aright, Bright);
                    }
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                } else if (Aleft > Bright) {
                    r = i - 1;
                } else {
                    l = i + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int[][][] ts = {
                { { 1, 3 }, { 3 } },
                { { 1, 3 }, { 2, 4 } },
                { { 1 }, { 1 } },
                { { 1, 3 }, { 2 } }
        };

        for (int[][] t : ts) {
            Solution s = new Solution();
            System.out.println(s.findMedianSortedArrays(t[0], t[1]));
        }
    }
}
