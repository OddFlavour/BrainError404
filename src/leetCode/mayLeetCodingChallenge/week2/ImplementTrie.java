package leetCode.mayLeetCodingChallenge.week2;

import java.util.HashMap;
import java.util.Map;

public class ImplementTrie {
    static class Trie {
        class TrieNode {
            Map<Character, TrieNode> children;
            boolean isEnding;

            public TrieNode(boolean isEnding) {
                this.children = new HashMap<>();
                this.isEnding = isEnding;
            }
        }

        TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TrieNode(false);
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            insert(root, word, 0);
        }

        public void insert(TrieNode node, String word, int index) {
            if (index < word.length()) {
                char c = word.charAt(index);

                // If it does not exist, add it
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode(false));
                }

                // TODO: update isEnding for midway
                if (word.length() - index <= 1) {
                    node.children.get(c).isEnding = true;
                }

                insert(node.children.get(c), word, index + 1);
            }
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            return search(root, word, 0);
        }

        public boolean search(TrieNode node, String word, int index) {
            if (index < word.length()) {
                char c = word.charAt(index);

                if (node.children.containsKey(c)) {
                    return search(node.children.get(c), word, index + 1);
                } else {
                    return false;
                }
            } else {
                return node.isEnding;
            }
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            return startsWith(root, prefix, 0);
        }

        public boolean startsWith(TrieNode node, String prefix, int index) {
            if (index < prefix.length()) {
                char c = prefix.charAt(index);

                if (node.children.containsKey(c)) {
                    return startsWith(node.children.get(c), prefix, index + 1);
                } else {
                    return false;
                }
            }

            return true;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
    }
}
