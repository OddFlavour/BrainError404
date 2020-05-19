package leetCode.mayLeetCodingChallenge.week1;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> mCount = new HashMap<>();

        for (char c : magazine.toCharArray()) {
            mCount.put(c, mCount.getOrDefault(c, 0) + 1);
        }

        for (char c : ransomNote.toCharArray()) {
            mCount.put(c, mCount.getOrDefault(c, 0) - 1);

            if (mCount.get(c) < 0) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        String[][] tests = {
                {"a", "b"},
                {"aa", "ab"},
                {"aa", "aab"},
                {"", ""},
                {"", "asd"},
                {"wejoj", "jwelvjajioweasdfjklasjjoj"}
        };

        for (String[] test : tests) {
            RansomNote s = new RansomNote();
            System.out.println(s.canConstruct(test[0], test[1]));
        }
    }
}
