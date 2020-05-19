package leetCode.mayLeetCodingChallenge.week1;

import java.util.HashMap;
import java.util.Map;

public class JewelsAndStones {
    public int numJewelsInStones(String J, String S) {
        int ans = 0;

        Map<Character, Integer> jewelCount = new HashMap<>();

        for (char c : J.toCharArray()) {
            jewelCount.put(c, 1);
        }

        for (char c : S.toCharArray()) {
            if (jewelCount.containsKey(c)) {
                ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        String[][] tests = {
                {"aA", "aAAbbbb"},
                {"z", "ZZ"},
                {"", ""},
                {"", "asdasd"},
                {"aA", "asdASDasdASD"}
        };

        for (String[] test : tests) {
            JewelsAndStones s = new JewelsAndStones();
            System.out.println(s.numJewelsInStones(test[0], test[1]));
        }
    }
}
