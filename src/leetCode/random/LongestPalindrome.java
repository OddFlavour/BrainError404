package leetCode.random;

public class LongestPalindrome {
    int lo, maxLen;

    public String longestPalindrome(String s) {
        String ans = "";

        // For each letter of the string, start expanding outwards to detect palindrome
        for (int i = 0; i < s.length(); i++) {
            findPalindrome(s, i, i);
            findPalindrome(s, i, i + 1);
        }

        return s.substring(lo, lo + maxLen);
    }

    private void findPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }

        if (right - (left + 1) > maxLen) {
            lo = left + 1;
            maxLen = right - lo;
        }
    }

    public static void main(String[] args) {
        String[] tests = {
                "babbaab",
                "babad",
                "cbbd",
                "aaaaaaaaaaa",
                "",
                "a"
        };

        int[] answers = {
                4,
                3,
                2,
                11,
                0,
                1
        };

        for (int i = 0; i < tests.length; i++) {
            LongestPalindrome s = new LongestPalindrome();
            String ans = s.longestPalindrome(tests[i]);
            System.out.println(ans);
            System.out.println(ans.length() == answers[i]);
        }
    }
}
