package leetCode.challenge30Day.week2;

import java.util.*;

public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        int[] sums = new int[nums.length + 1];
        sums[0] = 0;

        int curr = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                curr--;
            } else {
                curr++;
            }

            sums[i + 1] = curr;
        }

        int ans = 0;

        Map<Integer, Integer> starts = new HashMap<>();
        for (int i = 0; i < sums.length; i++) {
            Integer start = starts.get(sums[i]);
            if (start == null) {
                starts.put(sums[i], i);
            } else {
                int possibleAns = i - start;
                if (possibleAns > ans) {
                    ans = possibleAns;
                }
            }
        }

        return ans;
    }
    public static void main(String[] args) {
        List<List<Integer>> tests = new ArrayList<>();
        int[] expected = {2, 2, 18, 0, 0, 4, 4, 68, 6, 6};

        tests.add(new ArrayList<>(Arrays.asList(0, 1)));
        tests.add(new ArrayList<>(Arrays.asList(0, 1, 0)));
        tests.add(new ArrayList<>(Arrays.asList(
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                1, 1, 1, 1, 1, 1, 1, 1, 1
        )));
        tests.add(new ArrayList<>(Arrays.asList(0)));
        tests.add(new ArrayList<>());
        tests.add(new ArrayList<>(Arrays.asList(0, 1, 0, 0, 1, 0, 0, 1)));
        tests.add(new ArrayList<>(Arrays.asList(0, 1, 1, 0, 1, 1, 1, 0)));
        tests.add(new ArrayList<>(Arrays.asList(0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1)));
        tests.add(new ArrayList<>(Arrays.asList(1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1)));
        tests.add(new ArrayList<>(Arrays.asList(1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1)));

        for (int i = 0; i < tests.size(); i++) {
            ContiguousArray s = new ContiguousArray();
            int[] test = tests.get(i).stream().mapToInt(t -> t).toArray();

            assertTrue(s.findMaxLength(test), expected[i]);
        }
    }

    public static void assertTrue(int a, int b) {
        System.out.printf("%d == %d\n", a, b);
    }
}
