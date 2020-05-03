package leetCode.challenge30Day.week4;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int ans = 0;

        int n = nums.length;
        Map<Integer, Integer> partialSums = new HashMap<>();
        partialSums.put(0, 1);

        int prefixSum = 0;
        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];

            if (partialSums.containsKey(prefixSum - k)) {
                ans += partialSums.get(prefixSum - k);
            }

            partialSums.putIfAbsent(prefixSum, 0);
            partialSums.put(prefixSum, partialSums.get(prefixSum) + 1);
        }

        return ans;
    }
}
