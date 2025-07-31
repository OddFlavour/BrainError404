package neetcode.binarysearch;

/**
 * https://neetcode.io/problems/eating-bananas?list=neetcode150
 */
public class KokoEatingBananas {
    static class Solution {
        public int minEatingSpeed(int[] piles, int h) {
            int l = 1, r = -1;

            for (int x : piles) {
                r = Math.max(r, x);
            }

            int ans = r;

            while (l <= r) {
                int m = l + (r - l) / 2;
                int count = findCount(piles, m);

                // If it took longer than allowed (invalid), then it means we're eating too little bananas (k too small)
                if (count > h) {
                    l = m + 1;
                }
                // If it took shorter than allowed (valid), then it means we're eating too many bananas (k too big)
                else if (count <= h) {
                    r = m - 1;
                    ans = Math.min(ans, m);
                }
            }

            return ans;
        }

        private int findCount(int[] piles, int k) {
            int count = 0;

            for (int x : piles) {
                count += Math.ceil((double) x / k);
            }

            return count;
        }
    }

    public static void main(String[] args) {
        int[][] ts1 = {
//                { 1, 4, 3, 2 },
//                { 3,6,7,11 },
                { 1,1,1,999999999 }
        };

        int[] ts2 = {
//                7,
//                8,
                10
        };

        for (int i = 0; i < ts1.length; i++) {
            Solution s = new Solution();
            System.out.println(s.minEatingSpeed(ts1[i], ts2[i]));
        }
    }
}
