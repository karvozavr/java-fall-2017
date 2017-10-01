package ru.spbau.mit.java.karvozavr;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Trie implements Serializable {

    private int size = 0;
    private Node root;

    /**
     * Build Trie from String array.
     *
     * @param strings to be added to the Trie.
     */
    Trie(String[] strings) {
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

        size++;

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
        size--;

        return true;
    }

    /**
     * Size of the Trie in O(1) time.
     *
     * @return Size of the Trie.
     */
    public int size() {
        return size;
    }

    /**
     * Calculate how many elements in Trie start with given prefix in O(|prefix|) time.
     *
     * @param prefix to find elements in Trie
     * @return amount of elements, that start with given prefix.
     */
    int howManyStartsWithPrefix(String prefix) {
        if (contains(prefix)) {
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
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
            ArrayList<String> strings = new ArrayList<>();
            dfs(root, new StringBuilder(), strings);
            for (String element : strings) {
                writer.write(element);
                writer.newLine();
            }
        }
    }

    /**
     * Get the Trie from the input stream.
     * @param in stream
     * @throws IOException because of internal calls
     */
    public void deserialize(InputStream in) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                add(line);
            }
        }
    }

    /**
     * Dfs to take all the elements from the Trie and put them to ArrayList.
     * @param node of Trie
     * @param currentString string, that DFS has on current step
     * @param strings array to put found strings to
     */
    private void dfs(Node node, StringBuilder currentString, ArrayList<String> strings) {
        if (node.isTerminating) {
            strings.add(currentString.toString());
        }
        for (char c : node.children.keySet()) {
            currentString.append(c);
            dfs(node.getChild(c), currentString, strings);
            currentString.setLength(currentString.length() - 1);
        }
    }

    private static class Node {
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

        public boolean hasChildren() {
            return !children.isEmpty();
        }
    }
}
