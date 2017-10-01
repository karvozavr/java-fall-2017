package ru.spbau.mit.java.karvozavr;

import org.junit.jupiter.api.Test;

import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    @Test
    void testContains() {
        Trie trie = new Trie();
        assertFalse(trie.contains("elem"));
        trie.add("elem");
        assertTrue(trie.contains("elem"));
    }

    @Test
    void testAddSmoke() {
        Trie trie = new Trie();
        trie.add("elem");
        assertTrue(trie.contains("elem"));
    }

    @Test
    void testAddReturnValue() {
        Trie trie = new Trie();
        assertFalse(trie.add("elem"));
        assertTrue(trie.add("elem"));
    }

    @Test
    void testAddMany() {
        String strings[] = {
                "some string",
                "other string",
                "some same prefix string",
                "just a string",
                "some more string"
        };
        Trie trie = new Trie(strings);
        for (String s : strings) {
            assertTrue(trie.contains(s));
        }
    }

    @Test
    void testAddEmptyString() {
        Trie trie = new Trie();
        assertFalse(trie.contains(""));
        assertFalse(trie.add(""));
        assertTrue(trie.contains(""));
    }

    @Test
    void testRemoveSmoke() {
        Trie trie = new Trie();
        trie.add("elem");
        trie.remove("elem");
        assertFalse(trie.contains("elem"));
    }

    @Test
    void testRemoveReturnValue() {
        Trie trie = new Trie();
        trie.add("elem");
        assertTrue(trie.remove("elem"));
        assertFalse(trie.remove("elem"));
    }

    @Test
    void testRemoveEmpty() {
        Trie trie = new Trie();
        trie.add("");
        trie.remove("");
        assertFalse(trie.contains(""));
    }

    @Test
    void testRemoveMany() {
        String strings[] = {
                "some string",
                "other string",
                "some same prefix string",
                "just a string",
                "some more string"
        };
        Trie trie = new Trie(strings);
        trie.remove("some more string");
        for (int i = 0; i < strings.length - 1; i++) {
            assertTrue(trie.contains(strings[i]));
        }
        assertFalse(trie.contains(strings[strings.length - 1]));
    }

    @Test
    void testSize() {
        String strings[] = {
                "some string",
                "other string",
                "some same prefix string",
                "just a string",
                "some more string"
        };
        Trie trie = new Trie();
        assertEquals(0, trie.size());
        trie = new Trie(strings);
        assertEquals(5, trie.size());
        for (String s : strings) {
            trie.remove(s);
        }

        assertEquals(0, trie.size());
    }

    @Test
    void testContainsPrefix() {
        String strings[] = {
                "some string",
                "other string"
        };
        Trie trie = new Trie(strings);
        assertTrue(trie.contains_prefix("some"));
        assertTrue(trie.contains_prefix("other"));
        assertTrue(trie.contains_prefix(""));
        assertFalse(trie.contains_prefix("no"));
        assertFalse(trie.contains_prefix("some string with addition"));
    }

    @Test
    void testHowManyStartsWithPrefix() {
        String strings[] = {
                "some string",
                "other string",
                "some same prefix string",
                "just a string",
                "some more string"
        };
        Trie trie = new Trie(strings);
        assertEquals(3, trie.howManyStartsWithPrefix("some"));
        assertEquals(2, trie.howManyStartsWithPrefix("some s"));
        assertEquals(0, trie.howManyStartsWithPrefix("no such prefix"));
        assertEquals(5, trie.howManyStartsWithPrefix(""));
    }

    @Test
    void testSerialize() {
        String strings[] = {
                "some string",
                "other string",
                "some same prefix string",
                "just a string",
                "some more string"
        };
        OutputStream outputStream = new ByteArrayOutputStream();
        Trie trie = new Trie(strings);
        try {
            trie.serialize(outputStream);
        } catch (Exception e) {
            throw new Error("There has to be no exception, something went very wrong!");
        }
        String check[] = outputStream.toString().split("\n");
        assertEquals(strings.length, check.length);
        for (String s : check) {
            assertTrue(trie.contains(s));
        }
    }

    @Test
    void testDeserialize() {
        String strings[] = {
                "some string",
                "other string",
                "some same prefix string",
                "just a string",
                "some more string"
        };
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Trie trie = new Trie(strings);
        try {
            trie.serialize(outputStream);
        } catch (Exception e) {
            throw new Error("There has to be no exception, something went very wrong!");
        }

        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        Trie trie2 = new Trie();
        try {
            trie2.deserialize(inputStream);
        } catch (Exception e) {
            throw new Error("There has to be no exception, something went very wrong!");
        }

        assertEquals(5, trie2.size());
        for (String s : strings) {
            assertTrue(trie2.contains(s));
        }
    }
}
