package neetcode.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * https://neetcode.io/problems/minimum-window-with-characters?list=neetcode150
 */
public class MinimumWindowSubstring {
    static class Solution {
        public String minWindow(String s, String t) {
            // Answer indexes
            int left = 0, right = -1;

            Map<Character, Integer> tCount = new HashMap<>();

            for (int i = 0; i < t.length(); i++) {
                char currentChar = t.charAt(i);
                if (!tCount.containsKey(currentChar)) {
                    tCount.put(currentChar, 0);
                }

                tCount.put(currentChar, tCount.get(currentChar) + 1);
            }

            Map<Character, Integer> sCount = new HashMap<>();

            int l = 0, r = 0;
            while (r < s.length()) {
                char rightChar = s.charAt(r);
                if (!sCount.containsKey(rightChar)) {
                    sCount.put(rightChar, 0);
                }

                sCount.put(rightChar, sCount.get(rightChar) + 1);

                while (isValidAnswer(sCount, tCount)) {
                    if (right == -1 || r - l < right - left) {
                        left = l;
                        right = r;
                    }

                    char leftChar = s.charAt(l);
                    sCount.put(leftChar, sCount.get(leftChar) - 1);

                    l++;
                }

                r++;
            }

            return s.substring(left, right + 1);
        }

        private boolean isValidAnswer(Map<Character, Integer> sCount, Map<Character, Integer> tCount) {
            for (Map.Entry<Character, Integer> entry : tCount.entrySet()) {
                if (!sCount.containsKey(entry.getKey()) || sCount.get(entry.getKey()) < entry.getValue()) {
                    return false;
                }
            }

            return true;
        }
    }

    public static void main(String[] args) {
        String[][] ts = {
                { "xyz", "xyz" },
                { "OUZODYXAZV", "XYZ" },
                { "x", "y" },
                { "xy", "x" }
        };

        for (String[] t : ts) {
            Solution s = new Solution();
            System.out.println(s.minWindow(t[0], t[1]));
        }
    }
}
