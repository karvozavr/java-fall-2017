package ru.spbau.mit.java.fall2017.containers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    @Test
    void testListCreation() {
        LinkedList list = new LinkedList();
    }

    @Test
    void testAddSmoke() {
        LinkedList list = new LinkedList();
        String key = "This is key.";
        String value = "This is value.";
        list.add(key, value);
    }

    @Test
    void testAddBase() {
        LinkedList list = new LinkedList();
        String key = "This is key.";
        String value = "This is value.";
        list.add(key, value);
        assertEquals(value, list.getByKey(key));
    }

    @Test
    void testGetByKeyNull() {
        LinkedList list = new LinkedList();
        assertEquals(null, list.getByKey("key"));
    }

    @Test
    void testGetByKeyWrongKey() {
        LinkedList list = new LinkedList();
        String key = "This is key.";
        String value = "This is value.";
        list.add(key, value);
        assertEquals(null, list.getByKey("Not a key"));
    }

    @Test
    void testGetByKeyBase() {
        LinkedList list = new LinkedList();
        String key = "key";
        String value = "value";
        list.add(key, value);
        assertEquals(value, list.getByKey(key));
    }

    @Test
    void testAddTwoValues() {
        LinkedList list = new LinkedList();
        String key = "This is key.";
        String value = "This is value.";
        list.add(key, "Some old value.");
        list.add(key, value);
        assertEquals(value, list.getByKey(key));
    }

    @Test
    void TestRemoveByKeyNull() {
        LinkedList list = new LinkedList();
        assertEquals(null, list.removeByKey("key"));
    }

    @Test
    void TestRemoveByKeyBase() {
        LinkedList list = new LinkedList();
        String key = "key";
        String value = "value";
        list.add(key, value);
        assertEquals(value, list.removeByKey(key));
        assertEquals(null, list.removeByKey(key));
    }
}