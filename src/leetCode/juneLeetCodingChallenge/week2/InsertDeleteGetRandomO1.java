package leetCode.juneLeetCodingChallenge.week2;

import java.util.HashMap;
import java.util.Map;

public class InsertDeleteGetRandomO1 {
    // Solution from LeetCode
    class RandomizedSet {
        Map<Integer, Integer> valueToIndex;
        int[] expandable;
        int size = 0;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            valueToIndex = new HashMap<>();
            expandable = new int[100];
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (size == expandable.length) {
                int[] newExpandable = new int[expandable.length * 2];
                for (int i = 0; i < expandable.length; i++) {
                    newExpandable[i] = expandable[i];
                }

                expandable = newExpandable;
            }

            if (valueToIndex.containsKey(val)) return false;
            else {
                valueToIndex.put(val, size);
                expandable[size] = val;
                size++;

                return true;
            }
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!valueToIndex.containsKey(val)) return false;
            else {
                int index = valueToIndex.get(val);

                // Since we're removing index, get last element to replace its place
                expandable[index] = expandable[size - 1];
                valueToIndex.put(expandable[index], index);
                valueToIndex.remove(val);
                size--;

                return true;
            }
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return expandable[(int) (Math.random() * size)];
        }
    }
}
