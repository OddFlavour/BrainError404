package leetCode.challenge30Day.week1;

import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> sortedToAnagrams = new HashMap<>();
        for (String str : strs) {
            String sortedStr = sortString(str);

            // construct sortedToAnagrams
            List<String> anagrams = sortedToAnagrams.get(sortedStr);
            if (anagrams == null) {
                anagrams = new ArrayList<>();
                sortedToAnagrams.put(sortedStr, anagrams);
            }
            anagrams.add(str);
        }

        List<List<String>> ret = new ArrayList<>();
        for (List<String> anagrams : sortedToAnagrams.values()) {
            ret.add(anagrams);
        }

        return ret;
    }

    public String sortString(String s) {
        char[] charSet = s.toCharArray();
        Arrays.sort(charSet);
        return new String(charSet);
    }

    public static void main(String[] args) {
        String[] t1 = { "eat", "tea", "tan", "ate", "nat", "bat" };
        String[] t2 = { };
        String[] t3 = { "ate", "ate" };

        GroupAnagrams s = new GroupAnagrams();
        s.groupAnagrams(t1);
        s.groupAnagrams(t2);
        s.groupAnagrams(t3);
    }
}
