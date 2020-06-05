package leetCode.mayLeetCodingChallenge.week4;

import java.util.*;

public class SortCharactersByFrequency {
    public String frequencySort(String s) {
        int[] frequencies = new int[256];
        for (char c : s.toCharArray()) {
            frequencies[c]++;
        }

        Map<Integer, List<Character>> freqToChars = new HashMap<>();
        for (int i = 0; i < frequencies.length; i++) {
            int freq = frequencies[i];
            if (freq != 0) {
                if (!freqToChars.containsKey(freq)) freqToChars.put(freq, new ArrayList<>());

                freqToChars.get(freq).add((char) i);
            }
        }

        // Sort the frequencies
        Set<Integer> freqs = freqToChars.keySet();
        int[] inorder = new int[freqs.size()];

        int curr = 0;
        for (int freq : freqs) {
            inorder[curr++] = freq;
        }

        Arrays.sort(inorder);

        // Build the string
        StringBuilder sb = new StringBuilder();
        for (int i = inorder.length - 1; i >= 0; i--) {
            int freq = inorder[i];

            StringBuilder fragment = new StringBuilder();
            for (Character c : freqToChars.get(freq)) {
                // Add 'freq' number of the character
                for (int count = 0; count < freq; count++) {
                    fragment.append(c);
                }
            }

            sb.append(fragment.toString());
        }

        return sb.toString();
    }
}
