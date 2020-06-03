package leetCode.mayLeetCodingChallenge.week3;

public class PermutationInString {
    final int OFFSET = 97;

    public boolean checkInclusion(String s1, String s2) {
        int[] sCount = new int[26];
        for (char c : s1.toCharArray()) {
            sCount[c - OFFSET]++;
        }

        int left = 0, right = 0, count = s1.length();
        while (right < s2.length()) {
            if (sCount[s2.charAt(right++) - OFFSET]-- > 0) count--;

            if (count == 0) return true;

            if (right - left == s1.length() && ++sCount[s2.charAt(left++) - OFFSET] > 0) count++;
        }

        return false;
    }
}
