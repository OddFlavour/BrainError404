package leetCode.julyLeetCodingChallenge.week1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        digits[digits.length - 1] += 1;

        List<Integer> ans = Arrays.stream(digits).boxed().collect(Collectors.toList());

        boolean overflow = false;

        for (int i = ans.size() - 1; i >= 0; i--) {
            if (overflow) {
                ans.set(i, ans.get(i) + 1);
                overflow = false;
            }
            if (ans.get(i) >= 10) {
                ans.set(i, 0);
                overflow = true;
            }
        }

        // In the case that a new digit was introduced
        if (overflow == true) {
            ans.add(0, 1);
        }

        return ans.stream().mapToInt(i->i).toArray();
    }
}
