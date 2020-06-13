package leetCode.random;

public class LongestPalindrome {
    public String longestPalindrome(String s) {
        String ans = "";

        // For each letter of the string, start expanding outwards to detect palindrome
        for (int i = 0; i < s.length(); i++) {
            String p = findPalindrome(s, i - 1, i + 1);
            if (p.length() > ans.length()) {
                ans = p;
            }

            if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                p = findPalindrome(s, i - 1, i + 2);
                if (p.length() > ans.length()) {
                    ans = p;
                }
            }
        }

        return ans;
    }

    private String findPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }

        return s.substring(left + 1, right);
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
