package neetcode.arraysandhashing;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/top-k-frequent-elements
 * https://neetcode.io/problems/top-k-elements-in-list
 */
public class TopKFrequentElements {
    static class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            int[] ret = new int[k];

            Map<Integer, Integer> count = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            }

            for (int i = 0; i < k; i++) {
                int maxKey = Integer.MAX_VALUE;

                for (Integer key : count.keySet()) {
                    if (maxKey == Integer.MAX_VALUE || count.get(key) > count.get(maxKey)) {
                        maxKey = key;
                    }
                }

                ret[i] = maxKey;
                count.remove(maxKey);
            }

            return ret;
        }

        /**
         * When finding the K-frequent, uses buckets for easier/faster find
         * @param nums
         * @param k
         * @return
         */
        public int[] topKFrequentV2(int[] nums, int k) {
            int n = nums.length;

            List<Integer>[] buckets = new ArrayList[n + 1];

            Map<Integer, Integer> count = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            }

            for (Integer key : count.keySet()) {
                int index = count.get(key);

                if (buckets[index] == null) {
                    buckets[index] = new ArrayList<>();
                }

                buckets[index].add(key);
            }

            List<Integer> ret = new ArrayList<>();

            int counter = k;

            for (int i = n; i >= 0; i--) {
                if (buckets[i] != null) {
                    int threshold = Math.min(counter, buckets[i].size());
                    for (int j = 0; j < threshold; j++) {
                        ret.add(buckets[i].get(j));
                    }
                    counter -= threshold;
                }

                if (counter == 0) {
                    break;
                }
            }

            return ret.stream().mapToInt(i -> i).toArray();
        }
    }

}
