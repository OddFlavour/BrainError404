package leetCode.mayLeetCodingChallenge.week3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> answers = new ArrayList<>();

        Map<Character, Integer> pMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            pMap.put(c, pMap.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> sMap = new HashMap<>();

        int last = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If we found a letter in 'p'
            if (pMap.containsKey(c)) {
                // Add the letter
                sMap.put(c, sMap.getOrDefault(c, 0) + 1);


                for (Character key : sMap.keySet()) {
                    // If for a letter, sMap contains more count than pMap, then loop until no longer true
                    // Now verify if all counts are included
                    while (sMap.get(key) > pMap.get(key)) {
                        char first = s.charAt(last++);
                        sMap.put(first, sMap.get(first) - 1);
                    }
                }

                // Verify that all letters (not counts) are included
                if (sMap.size() == pMap.size()) {
                    // Verify that all counts are included
                    boolean isEqual = true;
                    for (Character key : sMap.keySet()) {
                        if (!sMap.get(key).equals(pMap.get(key))) {
                            isEqual = false;
                            break;
                        }
                    }

                    // If we found a match, update answer, remove the first letter, update first letter
                    if (isEqual) {
                        answers.add(last);

                        char first = s.charAt(last++);
                        sMap.put(first, sMap.get(first) - 1);
                    }
                }
            }
            // Otherwise we did not find a letter in 'p'
            else {
                last = i + 1;
                sMap.clear();
            }
        }

        return answers;
    }
}
