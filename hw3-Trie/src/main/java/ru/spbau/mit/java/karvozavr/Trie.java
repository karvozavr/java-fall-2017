package ru.spbau.mit.java.karvozavr;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Trie implements Serializable {

    private Node root;

    /**
     * Create empty Trie.
     */
    public Trie() {
        root = new Node();
    }

    /**
     * Build Trie from String array.
     *
     * @param strings to be added to the Trie.
     */
    public Trie(String[] strings) {
        root = new Node();
        for (String element : strings) {
            add(element);
        }
    }

    /**
     * Add element to the Trie in O(|element|) time.
     *
     * @param element to add to Trie.
     * @return true, if this element already exist in Trie.
     */
    public boolean add(String element) {
        if (contains(element)) {
            return true;
        }

        Node node = root;
        for (char c : element.toCharArray()) {
            node.startsWithThisPrefix++;
            if (node.getChild(c) == null) {
                node.addChild(c);
            }
            node = node.getChild(c);
        }

        node.isTerminating = true;

        return false;
    }

    /**
     * Check, if element exist in the Trie in O(|element|) time.
     *
     * @param element to find in Trie.
     * @return true, if this element already exist in Trie.
     */
    public boolean contains(String element) {
        Node node = root;
        for (char c : element.toCharArray()) {
            node = node.getChild(c);
            if (node == null) {
                return false;
            }
        }

        return node.isTerminating;
    }

    /**
     * Check, if prefix exist in the Trie in O(|prefix|) time.
     *
     * @param prefix to find in Trie.
     * @return true, if this prefix exist in Trie.
     */
    public boolean containsPrefix(String prefix) {
        Node node = root;
        for (char c : prefix.toCharArray()) {
            node = node.getChild(c);
            if (node == null) {
                return false;
            }
        }

        return true;
    }

    /**
     * Remove element, if it exist in Trie in O(|element|) time.
     *
     * @param element to remove from Trie.
     * @return true, if if this element exist in Trie.
     */
    public boolean remove(String element) {
        if (!contains(element)) {
            return false;
        }

        Node node = root;
        for (char c : element.toCharArray()) {
            node.startsWithThisPrefix--;
            if (node.getChild(c).startsWithThisPrefix <= 1) {
                node.removeChild(c);
                return true;
            }
            node = node.getChild(c);
        }

        node.isTerminating = false;

        return true;
    }

    /**
     * Size of the Trie in O(1) time.
     *
     * @return Size of the Trie.
     */
    public int size() {
        return root.startsWithThisPrefix;
    }

    /**
     * Calculate how many elements in Trie start with given prefix in O(|prefix|) time.
     *
     * @param prefix to find elements in Trie
     * @return amount of elements, that start with given prefix.
     */
    int howManyStartsWithPrefix(String prefix) {
        if (containsPrefix(prefix)) {
            Node node = root;
            for (char c : prefix.toCharArray()) {
                node = node.getChild(c);
            }
            return node.startsWithThisPrefix;
        } else {
            return 0;
        }
    }

    /**
     * Put the Trie to the output stream.
     * @param out stream
     * @throws IOException because of internal calls
     */
    public void serialize(OutputStream out) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(out)) {
            outputStream.writeObject(this);
        }
    }

    /**
     * Get the Trie from the input stream.
     * @param in stream
     * @throws IOException because of internal calls
     */
    public void deserialize(InputStream in) throws IOException {
        try (ObjectInputStream inputStream = new ObjectInputStream(in)) {
            Trie newTrie = (Trie)inputStream.readObject();
            this.root = newTrie.root;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("This is not normal not to find class!");
        }
    }

    private static class Node implements Serializable {
        private HashMap<Character, Node> children;
        private int startsWithThisPrefix = 0;
        private boolean isTerminating = false;

        {
            children = new HashMap<>();
        }

        /**
         * Adds child to this vertex.
         *
         * @param key to add as a child
         */
        public void addChild(char key) {
            children.put(key, new Node());
        }

        /**
         * Get child of this vertex by key.
         *
         * @param key to add as a child
         */
        public Node getChild(char key) {
            return children.get(key);
        }

        /**
         * Remove child of this vertex by key.
         *
         * @param key to remove from this vertex children
         */
        public void removeChild(char key) {
            children.remove(key);
        }

        /**
         * Check if there are children
         * @return if Node has children
         */
        public boolean hasChildren() {
            return !children.isEmpty();
        }
    }
}
