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
            assertTrue(table.contains(key));
        }
    }

    @Test
    void testContainsNegative() {
        HashTable table = new HashTable();
        for (int i = 0; i < 8; i++) {
            assertFalse(table.contains("key"));
        }
    }

    @Test
    void testContainsBase() {
        HashTable table = new HashTable();
        String key = "key";
        table.put(key, "value");
        assertTrue(table.contains(key));
        assertFalse(table.contains("Other key."));
    }

    @Test
    void testGetNull() {
        HashTable table = new HashTable();
        for (int i = 0; i < 8; i++) {
            assertNull(table.get("key"));
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
            assertNull(table.get("Other key"));
        }
    }

    @Test
    void testPut() {
        HashTable table = new HashTable();
        String key = "key";
        String value = "value";
        table.put(key, value);
        assertTrue(table.contains(key));
        assertEquals(value, table.get(key));
        assertFalse(table.contains("Other key."));
    }

    @Test
    void testPutExisting() {
        HashTable table = new HashTable();
        String key = "key";
        table.put(key, "value");
        assertEquals("value", table.put(key, "value2"));
        assertTrue(table.contains(key));
        assertEquals("value2", table.get(key));
    }

    @Test
    void testRemoveBase() {
        HashTable table = new HashTable();
        String key = "key";
        table.put(key, "value");
        assertEquals("value", table.remove(key));
        assertFalse(table.contains(key));
    }

    @Test
    void testRemoveNotInTable() {
        HashTable table = new HashTable();
        String key = "key";
        table.put(key, "value");
        assertNull(table.remove("other key"));
        assertTrue(table.contains(key));
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
            assertFalse(table.contains("key" + i));
        }
    }

}