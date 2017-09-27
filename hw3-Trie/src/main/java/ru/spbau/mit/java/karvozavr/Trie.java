package ru.spbau.mit.java.karvozavr;

import java.util.ArrayList;

public class Trie {

    private int size = 0;
    private Node root;

    /**
     * Build Trie from String array.
     * @param strings Strings to be added to the Trie.
     */
    Trie(String[] strings) {
        root = new Node();
        for (String element : strings) {
            add(element);
        }
    }

    /**
     * Add element to the Trie in O(|element|) time.
     * @param element String to add to Trie.
     * @return true, if this element already exist in Trie.
     */
    boolean add(String element) {
        if (contains(element)) {
            return true;
        }

        for (char c : )

        return false;
    }

    /**
     * Check, if element exist in the Trie in O(|element|) time.
     * @param element String to find in Trie.
     * @return true, if this element already exist in Trie.
     */
    boolean contains(String element) {
        return true;
    }

    /**
     * Remove element, if it exist in Trie in O(|element|) time.
     * @param element String to remove from Trie.
     * @return true, if if this element exist in Trie.
     */
    boolean remove(String element) {
        return true;
    }

    /**
     * Size of the Trie in O(1) time.
     * @return Size of the Trie.
     */
    int size() {
        return size;
    }

    /**
     * Calculate how many elements in Trie start with given prefix in O(|prefix|) time.
     * @param prefix prefix to find elements in Trie
     * @return Amount of elements, that start with given prefix.
     */
    int howManyStartsWithPrefix(String prefix) {
        return 0;
    }

    private class Node {
        char edge;
        private ArrayList<Node> children;

        public void addChild(Node child) {
            children.add(child);
        }

        public boolean hasChildren() {
            return !children.isEmpty();
        }
    }
}
