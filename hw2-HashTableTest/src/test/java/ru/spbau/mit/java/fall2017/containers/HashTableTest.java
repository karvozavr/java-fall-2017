package ru.spbau.mit.java.fall2017.containers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    @Test
    void testSizeEmpty() {
        HashTable table = new HashTable();
        assertEquals(0, table.size());
    }

    @Test
    void testSizeBase() {
        HashTable table = new HashTable();
        for (int i = 0; i < 100; ++i) {
            table.put("key" + i, "value" + i);
        }
        assertEquals(100, table.size());
    }

    @Test
    void contains() {
    }

    @Test
    void get() {
    }

    @Test
    void put() {
    }

    @Test
    void remove() {
    }

    @Test
    void clear() {
    }

}