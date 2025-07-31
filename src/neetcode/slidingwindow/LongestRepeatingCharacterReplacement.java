package neetcode.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://neetcode.io/problems/longest-repeating-substring-with-replacement?list=neetcode150
 */
public class LongestRepeatingCharacterReplacement {
    static class Solution {
        public int characterReplacement(String s, int k) {
            int[] charCount = new int[26];
            int max = 0;

            for (int i = 0; i < s.length(); i++) {
                int currCharIndex = (int) s.charAt(i) - (int) 'A';
                charCount[currCharIndex]++;

                int maxCharIndex = findMaxIndex(charCount);
                int charCountSum = sum(charCount);

                if (charCountSum - charCount[maxCharIndex] > k) {
                    int leftCharIndex = (int) s.charAt(i - charCountSum + 1) - (int) 'A';
                    charCount[leftCharIndex]--;
                    charCountSum--;
                }

                max = Math.max(charCountSum, max);
            }

            return max;
        }

        private int findMaxIndex(int[] charCount) {
            int maxIndex = 0;

            for (int i = 0; i < charCount.length; i++) {
                if (charCount[i] > charCount[maxIndex]) {
                    maxIndex = i;
                }
            }

            return maxIndex;
        }

        private int sum(int[] charCount) {
            int sum = 0;

            for (int count : charCount) {
                sum += count;
            }

            return sum;
        }
    }

    public static void main(String[] args) {
        List<List<String>> ts = new ArrayList<>();

        ts.add(Arrays.asList("XYYX", "2"));
        ts.add(Arrays.asList("AAABABB", "1"));
        ts.add(Arrays.asList("ABABABA", "3"));

        Solution s = new Solution();

        for (List<String> t : ts) {
            System.out.println(s.characterReplacement(t.get(0), Integer.parseInt(t.get(1))));
        }
    }
}
