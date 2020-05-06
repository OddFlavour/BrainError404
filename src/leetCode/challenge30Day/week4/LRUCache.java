package leetCode.challenge30Day.week4;

import java.util.HashMap;
import java.util.Map;

/**
 * To make helper methods ('evict', 'insert') cleaner, we can use a dummy head and tail
 * i.e a head and a tail that can never be evicted, existing solely for the purpose of clean pointer assignments
 */
public class LRUCache {
    class DLLNode {
        DLLNode prev, next;
        int key, value;

        public DLLNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public DLLNode(int key, int value, DLLNode prev, DLLNode next) {
            this(key, value);

            this.prev = prev;
            this.next = next;
        }
    }

    Map<Integer, DLLNode> cache = new HashMap<>();

    DLLNode lruNode;  // head
    DLLNode mruNode;  // tail

    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        DLLNode node = cache.get(key);
        if (node == null) return -1;

        // Update the usage history
        update(node);

        return node.value;
    }

    public void put(int key, int value) {
        boolean exists = get(key) != -1;

        if (!exists) {
            capacity--;
            if (capacity < 0) evict();
        }

        insert(key, value, exists);
    }

    private void evict() {
        // Time to evict
        if (lruNode == mruNode) {
            mruNode = null;
        }
        DLLNode evictee = lruNode;
        lruNode = lruNode.next;

        // Prep for garbage collect
        if (evictee.next != null) {
            evictee.next.prev = null;
        }
        evictee.next = null;

        cache.remove(evictee.key);
    }

    private void insert(int key, int value, boolean exists) {
        // Update usage history
        DLLNode insertee = cache.getOrDefault(key, new DLLNode(key, value));
        if (lruNode == null && mruNode == null) {
            lruNode = mruNode = insertee;
        } else if (!exists) {
            insertee.prev = mruNode;
            mruNode.next = mruNode = insertee;
        }

        insertee.value = value;
        cache.put(key, insertee);
    }

    private void update(DLLNode node) {
        if (mruNode == node) {
            return;
        } else if (lruNode == node) {
            node.next.prev = null;
            lruNode = node.next;
        } else {
            // take out 'node' and fix the chain
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        node.prev = mruNode;
        mruNode.next = mruNode = node;
    }
}
