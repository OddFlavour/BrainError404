package leetCode.mayLeetCodingChallenge.week2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindTheTownJudge {
    public int findJudge(int N, int[][] trust) {
        Map<Integer, Integer> trustCount = new HashMap<>();
        Set<Integer> isTrustingSomeone = new HashSet<>();

        if (N == 1) return 1;

        for (int[] t : trust) {
            trustCount.put(t[1], trustCount.getOrDefault(t[1], 0) + 1);
            isTrustingSomeone.add(t[0]);
        }

        for (Integer key : trustCount.keySet()) {
            if (trustCount.get(key) == N - 1) {
                if (!isTrustingSomeone.contains(key)) {
                    return key;
                }
            }
        }

        return -1;
    }
}
