package leetCode.juneLeetCodingChallenge.week2;

import java.util.LinkedList;
import java.util.Queue;

public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        Queue<Integer>[] map = new LinkedList[256];

        for (int i = 0; i < t.length(); i++) {
            int curr = t.charAt(i);
            if (map[curr] == null) map[curr] = new LinkedList<>();

            map[curr].add(i);
        }

        int last = 0;
        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(i);

            if (map[curr] == null)
                return false;

            while (!map[curr].isEmpty() && last > map[curr].peek()) {
                map[curr].poll();
            }

            if (map[curr].isEmpty()) return false;
            else last = map[curr].poll();
        }

        return true;
    }

    public boolean isSubsequence2(String s, String t) {
        int curr = 0;

        for (int i = 0; i < s.length(); i++) {
            boolean exit = false;
            while (!exit) {
                if (curr >= t.length()) return false;
                if (t.charAt(curr) == s.charAt(i))
                    exit = true;

                curr++;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[] testSs = {
                "abc",
                "axc",
                "",
                "abc",
                "aaaa"
        };

        String[] testTs = {
                "ahbgdc",
                "ahbgdc",
                "ahbgdc",
                "",
                "aaa"
        };

        for (int i = 0; i < testSs.length; i++) {
            IsSubsequence s = new IsSubsequence();
            System.out.println(s.isSubsequence2(testSs[i], testTs[i]));
        }
    }
}
