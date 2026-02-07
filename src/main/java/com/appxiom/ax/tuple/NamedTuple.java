/**
 * A named tuple implementation for Java that stores elements as key-value pairs using a Map.
 * 
 * @author Robin Panicker
 * @version 1.0.1
 * @since 0.1.0
 * 
 */
package com.appxiom.ax.tuple;

import java.util.Map;
import java.util.Objects;

/**
 * Represents a tuple where each element is associated with a specific name
 */
public class NamedTuple {

    /**
     * The internal map storing the named elements of the tuple.
     */
    private final Map<String, Object> map;

    /**
     * Creates a new NamedTuple with the specified map of name-value pairs.
     *
     * @param map the map containing the named elements
     * @return a new NamedTuple containing the specified map
     */
    public static NamedTuple of(Map<String, Object> map) {
        return new NamedTuple(map);
    }

    /**
     * Private constructor to prevent direct instantiation.
     *
     * @param map the map containing the named elements
     */
    private NamedTuple(Map<String, Object> map) {
        this.map = map;
    }

    /**
     * Retrieves the value associated with the specified name.
     *
     * @param key the name of the element to retrieve
     * @return the value associated with the key, or {@code null} if the key is not
     *         found
     */
    public Object getObject(String key) {
        return map.get(key);
    }

    /**
     * Retrieves the element at the specified index and casts it to the inferred
     * type.
     * Use with caution as it may throw a ClassCastException at runtime if the type
     * is incorrect.
     *
     * @param <T> the type to cast the element to
     * @param key the name of the element to retrieve
     * @return the element at the specified index, cast to type T
     * @throws ClassCastException if the element cannot be cast to the
     *                            specified type
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) map.get(key);
    }

    /**
     * Retrieves the element at the specified index and casts it to the specified
     * class type.
     *
     * @param <T>  the type to cast the element to
     * @param key  the name of the element to retrieve
     * @param type the class of the type to cast the element to
     * @return the element at the specified index, cast to the specified type
     * @throws ClassCastException if the element cannot be cast to the
     *                            specified type
     */
    public <T> T get(String key, Class<T> type) {
        return type.cast(map.get(key));
    }

    /**
     * Returns the number of named elements in this tuple.
     *
     * @return the size of the named tuple
     */
    public int size() {
        return map.size();
    }

    /**
     * Returns a string representation of the named tuple, typically following
     * the format of the underlying Map.
     *
     * @return a string representation of this named tuple
     */
    @Override
    public String toString() {
        return map.toString();
    }

    /**
     * Compares this named tuple to the specified object for equality.
     * Two named tuples are considered equal if they have the same name-value pairs.
     *
     * @param o the object to compare with
     * @return {@code true} if the specified object is equal to this named tuple,
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        NamedTuple that = (NamedTuple) o;
        return Objects.equals(map, that.map);
    }

    /**
     * Returns a hash code value for this named tuple.
     *
     * @return a hash code value for this named tuple
     */
    @Override
    public int hashCode() {
        return Objects.hash(map);
    }
}
