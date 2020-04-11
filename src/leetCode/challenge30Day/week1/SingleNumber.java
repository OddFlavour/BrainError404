package leetCode.challenge30Day.week1;

import java.util.HashMap;
import java.util.Map;

public class SingleNumber {

    public static void main(String[] args) {
        int[] t1 = { 2, 2, 1 };
        int[] t2 = { 4, 1, 2, 1, 2 };

        SingleNumber s = new SingleNumber();
        System.out.println(s.singleNumber(t1));
        System.out.println(s.singleNumber(t2));
    }

    public int singleNumber(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();

        for (int num : nums) {
            if (map.get(num) == null) {
                map.put(num, true);
            } else {
                map.put(num, false);
            }
        }

        for (int num : nums) {
            if (map.get(num)) {
                return num;
            }
        }

        return nums[0];
    }

    public int bestSoln(int[] nums) {
        // a xor b xor a = (a xor a) xor b = 0 xor b = b
        int ans = 0;

        for (int i : nums) {
            ans ^= i;
        }

        return ans;
    }
}
