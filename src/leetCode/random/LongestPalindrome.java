package leetCode.random;

import java.util.Arrays;

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
    
    public String findLongestPalindrome(String s) {
        if (s==null || s.length()==0)
            return "";

        char[] s2 = addBoundaries(s.toCharArray());
        int[] p = new int[s2.length];
        int c = 0, r = 0; // Here the first element in s2 has been processed.
        int m = 0, n = 0; // The walking indices to compare if two elements are the same.
        for (int i = 1; i<s2.length; i++) {
            // This would be case 3
            // This would be palindrome #4, where there could exist a palindrome beyond 'r'
            if (i>r) {
                p[i] = 0; m = i-1; n = i+1;
                // Otherwise we are still within palindrome #1, so we can find palindrome #2 and #3.
                // Palindrome #3 will be handled by 'i2'
            } else {
                int i2 = c*2-i;
                // If palindrome #3's length is less than the length between right bound and 'i + 1'
                // E.g ix.R, then the length would be 2 where x=i+1
//                if (p[i2]<(r-i-1)) {
                /*
                don't think the '-1' here is needed
                because 'p[i2]' acts like a wing, as long as it's within the bounds of palindrome #1, then there is no possible larger palindrome
                for example: |c|a|b|a|b|a|c|
                                  ^   ^    ^
                                 i2   i    r

                here we can see that because p[i2]=3, which is strictly less than (r - i), which means it's within palindrome #1
                , therefore by the property of palindrome, palindrome #1 == palindrome #2

                palindrome #1: |c|a|b|a|b|a|c|
                palindrome #2:   |a|b|a|
                palindrome #3:       |a|b|a|
                */
                if (p[i2]<(r-i)) {
                    // Inside here would be case 1, where len(P#2) == len(P#3)
                    p[i] = p[i2];
                    m = -1; // This signals bypassing the while loop below.
                    // Otherwise, palindrome #3's length exceeds the right bound and 'i + 1'
                } else {
                    // This would be case 2, where we utilize as much of the previously computed information
                    // This part basically helps reduce the amount of computation
                    p[i] = r-i;
                    n = r+1; m = i*2-n;
                }
            }
            // This is just to expand out from center at 'i'
            // m = left pointer
            // n = right pointer
            while (m>=0 && n<s2.length && s2[m]==s2[n]) {
                p[i]++; m--; n++;
            }

            /*
            Update 'r' if we've exceeded the bound

            Since we're updating 'r', we also need to update 'c', which is the center of the palindrome that spans
            all the way to 'r'
             */
            if ((i+p[i])>r) {
                c = i; r = i+p[i];
            }
        }

        // Find largest palindrome
        int len = 0; c = 0;
        for (int i = 1; i<s2.length; i++) {
            if (len<p[i]) {
                len = p[i]; c = i;
            }
        }

        // Extract out the largest palindrome
        char[] ss = Arrays.copyOfRange(s2, c-len, c+len+1);
        return String.valueOf(removeBoundaries(ss));
    }

    private char[] addBoundaries(char[] cs) {
        if (cs==null || cs.length==0)
            return "||".toCharArray();

        char[] cs2 = new char[cs.length*2+1];
        for (int i = 0; i<(cs2.length-1); i = i+2) {
            cs2[i] = '|';
            cs2[i+1] = cs[i/2];
        }
        cs2[cs2.length-1] = '|';
        return cs2;
    }

    private char[] removeBoundaries(char[] cs) {
        if (cs==null || cs.length<3)
            return "".toCharArray();

        char[] cs2 = new char[(cs.length-1)/2];
        for (int i = 0; i<cs2.length; i++) {
            cs2[i] = cs[i*2+1];
        }
        return cs2;
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

//        for (int i = 0; i < tests.length; i++) {
//            LongestPalindrome s = new LongestPalindrome();
//            String ans = s.longestPalindrome(tests[i]);
//            System.out.println(ans);
//            System.out.println(ans.length() == answers[i]);
//
//            LongestPalindrome s2 = new LongestPalindrome();
//            String ans2 = s2.longestPalindromeDP(tests[i]);
//            System.out.printf("(%s, %s) - %s\n", ans, ans2, ans.equals(ans2));
//            System.out.println(s.findLongestPalindrome(tests[i]).length() == answers[i]);
//        }
        LongestPalindrome s = new LongestPalindrome();
        s.findLongestPalindrome("ccc");
    }
}
