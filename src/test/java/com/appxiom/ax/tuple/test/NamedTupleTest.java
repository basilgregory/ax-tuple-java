package com.appxiom.ax.tuple.test;

import org.junit.jupiter.api.Test;

import com.appxiom.ax.tuple.NamedTuple;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

/**
 * Unit tests for the NamedTuple class.
 * 
 * @version 1.0.0
 * @since 0.1.0
 */
public class NamedTupleTest {

    /**
     * Tests that the constructor correctly initializes the named tuple and the size
     * method returns the expected count.
     */
    @Test
    public void testConstructorAndSize() {
        NamedTuple tuple0 = NamedTuple.of(Map.of());
        assertEquals(0, tuple0.size());
        NamedTuple tuple1 = NamedTuple.of(Map.of("key1", "value1"));
        assertEquals(1, tuple1.size());
    }

    /**
     * Tests the getObject method to ensure it returns the correct value for a given
     * key as an Object.
     */
    @Test
    public void testGetObject() {
        NamedTuple tuple = NamedTuple.of(Map.of("name", "John", "age", 30));

        assertEquals("John", tuple.getObject("name"));
        assertEquals(30, tuple.getObject("age"));
        assertNull(tuple.getObject("nonexistent"));
    }

    /**
     * Tests the inferred get method to ensure it correctly casts the value
     * associated with a key to the expected type.
     */
    @Test
    public void testInferredGet() {
        NamedTuple tuple = NamedTuple.of(Map.of("name", "John", "age", 30));

        String name = tuple.get("name");
        Integer age = tuple.get("age");
        assertEquals("John", name);
        assertEquals(30, age);
    }

    /**
     * Tests the get method with an explicit Class type to ensure correct casting
     * and exception handling for a given key.
     */
    @Test
    public void testClassTypeGet() {
        NamedTuple tuple = NamedTuple.of(Map.of("name", "John", "age", 30));

        assertEquals("John", tuple.get("name", String.class));
        assertEquals(30, tuple.get("age", Integer.class));
        assertThrows(ClassCastException.class, () -> tuple.get("name", Integer.class));
    }

    /**
     * Tests the toString method for the expected string representation, matching
     * the underlying map.
     */
    @Test
    public void testToString() {
        NamedTuple tuple = NamedTuple.of(Map.of("key", "value"));
        assertEquals(Map.of("key", "value").toString(), tuple.toString());
    }

    /**
     * Tests the equals and hashCode methods to ensure correct equality logic and
     * hash consistency for named tuples.
     */
    @Test
    public void testEqualsAndHashCode() {
        NamedTuple nt1 = NamedTuple.of(Map.of("k1", "v1"));
        NamedTuple nt2 = NamedTuple.of(Map.of("k1", "v1"));
        NamedTuple nt3 = NamedTuple.of(Map.of("k1", "v2"));

        assertEquals(nt1, nt2);
        assertNotEquals(nt1, nt3);
        assertNotEquals(nt1, null);
        assertNotEquals(nt1, new Object());

        assertEquals(nt1.hashCode(), nt2.hashCode());
        assertNotEquals(nt1.hashCode(), nt3.hashCode());
    }

    /**
     * Tests the usage of NamedTuple as a key in a HashMap.
     */
    @Test
    public void testHashMapUsage() {
        java.util.HashMap<NamedTuple, String> map = new java.util.HashMap<>();
        NamedTuple key1 = NamedTuple.of(Map.of("id", 1));
        NamedTuple key2 = NamedTuple.of(Map.of("id", 1));
        NamedTuple key3 = NamedTuple.of(Map.of("id", 2));

        map.put(key1, "value1");

        assertTrue(map.containsKey(key1));
        assertTrue(map.containsKey(key2)); // Different object, same content
        assertEquals("value1", map.get(key2));

        map.put(key2, "value2");
        assertEquals(1, map.size());
        assertEquals("value2", map.get(key1));

        assertFalse(map.containsKey(key3));
    }
}
