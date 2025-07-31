package neetcode.arraysandhashing;

import java.util.*;

/**
 * https://leetcode.com/problems/group-anagrams/
 * https://neetcode.io/problems/anagram-groups
 */
public class GroupAnagrams {
    static class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> ret = new ArrayList<>();

            int n = strs.length;

            int OFFSET = 97;
            int[][] characterized = new int[n][26];

            for (int j = 0; j < strs.length; j++) {
                for (int i = 0; i < strs[j].length(); i++) {
                    characterized[j][((int) strs[j].charAt(i)) - OFFSET]++;
                }
            }

            Map<String, List<String>> characterizedToOriginals = new HashMap<>();

            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    sb.append(characterized[i][j]);
                    sb.append("#");
                }

                String sbResult = sb.toString();

                if (!characterizedToOriginals.containsKey(sbResult)) {
                    characterizedToOriginals.put(sbResult, new ArrayList<>());
                }

                characterizedToOriginals.get(sbResult).add(strs[i]);
            }

            return new ArrayList<>(characterizedToOriginals.values());
        }

        /**
         * Faster implementation of the key "hashing"
         */
        public List<List<String>> groupAnagramsV2(String[] strs) {
            List<List<String>> ret = new ArrayList<>();

            int n = strs.length;

            int OFFSET = 97;
            int[][] characterized = new int[n][26];

            for (int j = 0; j < strs.length; j++) {
                for (int i = 0; i < strs[j].length(); i++) {
                    characterized[j][((int) strs[j].charAt(i)) - OFFSET]++;
                }
            }

            Map<Integer, List<String>> characterizedToOriginals = new HashMap<>();

            for (int i = 0; i < n; i++) {
                int sbResult = Arrays.hashCode(characterized[i]);

                if (!characterizedToOriginals.containsKey(sbResult)) {
                    characterizedToOriginals.put(sbResult, new ArrayList<>());
                }

                characterizedToOriginals.get(sbResult).add(strs[i]);
            }

            return new ArrayList<>(characterizedToOriginals.values());
        }
    }

}
