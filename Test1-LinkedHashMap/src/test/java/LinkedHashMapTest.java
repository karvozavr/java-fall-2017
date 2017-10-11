import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedHashMapTest {

    @Test
    static void testPut() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        assertFalse(map.containsKey("key"));
        map.put("key", 1);
        assertTrue(map.containsKey("key"));
    }

    @Test
    static void testGet() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        assertFalse(map.containsKey("key"));
        map.put("key", 1);
        assertEquals(new Integer(1), map.get("key"));
    }

    @Test
    static void testSize() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("key" + i, i);
        }
        assertEquals(10, map.size());
    }
}