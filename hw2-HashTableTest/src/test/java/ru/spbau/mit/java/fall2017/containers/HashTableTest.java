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
    void testContainsPositive() {
        HashTable table = new HashTable();
        String key = "key";
        table.put(key, "value");
        for (int i = 0; i < 8; i++) {
            assertEquals(true, table.contains(key));
        }
    }

    @Test
    void testContainsNegative() {
        HashTable table = new HashTable();
        for (int i = 0; i < 8; i++) {
            assertEquals(false, table.contains("key"));
        }
    }

    @Test
    void testContainsBase() {
        HashTable table = new HashTable();
        String key = "key";
        table.put(key, "value");
        assertEquals(true, table.contains(key));
        assertEquals(false, table.contains("Other key."));
    }

    @Test
    void testGetNull() {
        HashTable table = new HashTable();
        for (int i = 0; i < 8; i++) {
            assertEquals(null, table.get("key"));
        }
    }

    @Test
    void testGetBase() {
        HashTable table = new HashTable();
        String key = "key";
        String value = "value";
        table.put(key, value);
        for (int i = 0; i < 8; i++) {
            assertEquals(value, table.get("key"));
            assertEquals(null, table.get("Other key"));
        }
    }

    @Test
    void testPut() {
        HashTable table = new HashTable();
        String key = "key";
        String value = "value";
        table.put(key, value);
        assertEquals(true, table.contains(key));
        assertEquals(value, table.get(key));
        assertEquals(false, table.contains("Other key."));
    }

    @Test
    void testPutExisting() {
        HashTable table = new HashTable();
        String key = "key";
        table.put(key, "value");
        assertEquals("value", table.put(key, "value2"));
        assertEquals(true, table.contains(key));
        assertEquals("value2", table.get(key));
    }

    @Test
    void testRemoveBase() {
        HashTable table = new HashTable();
        String key = "key";
        table.put(key, "value");
        assertEquals("value", table.remove(key));
        assertEquals(false, table.contains(key));
    }

    @Test
    void testRemoveNotInTable() {
        HashTable table = new HashTable();
        String key = "key";
        table.put(key, "value");
        assertEquals(null, table.remove("other key"));
        assertEquals(true, table.contains(key));
    }

    @Test
    void testClear() {
        HashTable table = new HashTable();
        for (int i = 0; i < 100; i++) {
            table.put("key" + i, "val" + i);
        }
        table.clear();
        assertEquals(0, table.size());
        for (int i = 0; i < 100; i++) {
            assertEquals(false, table.contains("key" + i));
        }
    }

}