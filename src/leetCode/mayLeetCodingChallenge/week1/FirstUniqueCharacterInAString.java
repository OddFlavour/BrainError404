package leetCode.mayLeetCodingChallenge.week1;

public class FirstUniqueCharacterInAString {
    final int ASCII_OFFSET = 97;

    public int firstUniqChar(String s) {
        int[] letters = new int[26];

        return rec(s, 0, letters);
    }

    private int rec(String s, int i, int[] letters) {
        if (i >= s.length()) return -1;

        int index = s.charAt(i) - ASCII_OFFSET;
        letters[index]++;

        int result = rec(s, i + 1, letters);
        if (letters[index] == 1) {
            result = i;
        }

        return result;
    }

    public static void main(String[] args) {
        String[] tests = {
                "leetcode",
                "loveleetcode",
                "aabbcc",
                "abcabc",
                "abcab"
        };

        for (String test : tests) {
            FirstUniqueCharacterInAString s = new FirstUniqueCharacterInAString();
            System.out.println(s.firstUniqChar(test));
        }
    }
}
