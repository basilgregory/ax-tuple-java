package com.appxiom.ax.tuple.test;

import org.junit.jupiter.api.Test;

import com.appxiom.ax.tuple.Tuple;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

/**
 * Unit tests for the Tuple class.
 * 
 * @version 1.0.7
 * @since 0.1.0
 */
public class TupleTest {

    /**
     * Tests that the constructor correctly initializes the tuple and the size
     * method returns the expected count.
     */
    @Test
    public void testConstructorAndSize() {
        Tuple tuple0 = Tuple.of();
        assertEquals(0, tuple0.size());

        Tuple tuple1 = Tuple.of("one");
        assertEquals(1, tuple1.size());

        Tuple tuple2 = Tuple.of("one", 2);
        assertEquals(2, tuple2.size());
    }

    /**
     * Tests the getObject method to ensure it returns the correct element as an
     * Object.
     */
    @Test
    public void testGetObject() {
        Tuple tuple = Tuple.of("string", 123, 45.6);
        assertEquals("string", tuple.getObject(0));
        assertEquals(123, tuple.getObject(1));
        assertEquals(45.6, tuple.getObject(2));
    }

    /**
     * Tests the inferred get method to ensure it correctly casts elements to the
     * expected type.
     */
    @Test
    public void testInferredGet() {
        Tuple tuple = Tuple.of("string", 123);
        String s = tuple.get(0);
        Integer i = tuple.get(1);
        assertEquals("string", s);
        assertEquals(123, i);
    }

    /**
     * Tests the get method with an explicit Class type to ensure correct casting
     * and exception handling.
     */
    @Test
    public void testClassTypeGet() {
        Tuple tuple = Tuple.of("string", 123);
        assertEquals("string", tuple.get(0, String.class));
        assertEquals(123, tuple.get(1, Integer.class));
        assertThrows(ClassCastException.class, () -> tuple.get(0, Integer.class));
    }

    /**
     * Tests that accessing an index out of bounds throws an
     * ArrayIndexOutOfBoundsException.
     */
    @Test
    public void testOutOfBounds() {
        Tuple tuple = Tuple.of("one");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> tuple.getObject(1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> tuple.getObject(-1));
    }

    /**
     * Tests the toString method for the expected string representation.
     */
    @Test
    public void testToString() {
        Tuple tuple = Tuple.of("one", 2);
        assertEquals(Arrays.toString(new Object[] { "one", 2 }), tuple.toString());
    }

    /**
     * Tests the equals and hashCode methods to ensure correct equality logic and
     * hash consistency.
     */
    @Test
    public void testEqualsAndHashCode() {
        Tuple t1 = Tuple.of("a", "b");
        Tuple t2 = Tuple.of("a", "b");
        Tuple t3 = Tuple.of("a", "c");
        Tuple t4 = Tuple.of("a", "b", "c");

        assertEquals(t1, t2);
        assertNotEquals(t1, t3);
        assertNotEquals(t1, t4);
        assertNotEquals(t1, null);
        assertNotEquals(t1, "not a tuple");

        assertEquals(t1.hashCode(), t2.hashCode());
        assertNotEquals(t1.hashCode(), t3.hashCode());
    }

    /**
     * Tests the usage of Tuple as a key in a HashMap.
     */
    @Test
    public void testHashMapUsage() {
        java.util.HashMap<Tuple, String> map = new java.util.HashMap<>();
        Tuple key1 = Tuple.of("a", 1);
        Tuple key2 = Tuple.of("a", 1);
        Tuple key3 = Tuple.of("b", 2);

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
