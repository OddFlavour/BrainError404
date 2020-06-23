package leetCode.juneLeetCodingChallenge.week1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPickWithWeight {
    static class Solution {
        Random rng;
        List<Integer> wheel;

        int size = 0;

        public Solution(int[] w) {
            rng = new Random();
            wheel = new ArrayList<>();

            for (int x : w) {
                size += x;
                wheel.add(size);
            }
        }

        public int pickIndex() {
            int target = rng.nextInt(size);

            // Binary search
            int l = 0, r = wheel.size() - 1;
            while (l < r) {
                int mid = (l + r) / 2;

                if (wheel.get(mid) <= target) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }

            return l;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution(new int[] {5, 1});

        int count = 10000000;
        int zeros = 0, ones = 0;
        for (int i = 0; i < count; i++) {
            if (s.pickIndex() == 0) zeros++;
            else ones++;
        }

        System.out.println((double) zeros / count);
        System.out.println((double) ones / count);
    }
}
