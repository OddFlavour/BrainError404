package leetCode.julyLeetCodingChallenge.week2;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        // Empty set
        ans.add(new ArrayList<>());

        for (int num : nums) {
            int size = ans.size();
            for (int i = 0; i < size; i++) {
                List<Integer> oldSet = ans.get(i);
                List<Integer> newSet = new ArrayList<>();

                for (int n : oldSet) {
                    newSet.add(n);
                }

                newSet.add(num);
                ans.add(newSet);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(12 * 0x02);
    }
}
