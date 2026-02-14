/**
 * A simple immutable Tuple implementation for Java that can hold an arbitrary number of elements.
 * 
 * @author Robin Panicker
 * @version 1.0.7
 * @since 0.1.0
 */
package com.appxiom.ax.tuple;

import java.util.Arrays;

/**
 * Represents an immutable sequence of elements.
 */
public final class Tuple {

    /**
     * The internal array of elements stored in the tuple.
     */
    private final Object[] elements;

    /**
     * Creates a new Tuple with the specified elements.
     *
     * @param elements the elements to be included in the tuple
     * @return a new Tuple containing the specified elements
     */
    public static Tuple of(Object... elements) {
        return new Tuple(elements);
    }

    /**
     * Private constructor to prevent direct instantiation.
     *
     * @param elements the elements to be included in the tuple
     */
    private Tuple(Object... elements) {
        this.elements = elements;
    }

    /**
     * Retrieves the element at the specified index as an Object.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     */
    public Object getObject(int index) {
        return elements[index];
    }

    /**
     * Retrieves the element at the specified index and casts it to the inferred
     * type.
     * Use with caution as it may throw a ClassCastException at runtime if the type
     * is incorrect.
     *
     * @param <T>   the type to cast the element to
     * @param index the index of the element to retrieve
     * @return the element at the specified index, cast to type T
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     */
    @SuppressWarnings("unchecked")
    public <T> T get(int index) {
        return (T) elements[index];
    }

    /**
     * Retrieves the element at the specified index and casts it to the specified
     * class type.
     *
     * @param <T>   the type to cast the element to
     * @param index the index of the element to retrieve
     * @param type  the class of the type to cast the element to
     * @return the element at the specified index, cast to the specified type
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     * @throws ClassCastException             if the element cannot be cast to the
     *                                        specified type
     */
    public <T> T get(int index, Class<T> type) {
        Object value = elements[index];
        return type.cast(value);
    }

    /**
     * Returns the number of elements in this tuple.
     *
     * @return the size of the tuple
     */
    public int size() {
        return elements.length;
    }

    /**
     * Returns a string representation of the tuple, consisting of the elements
     * wrapped in square brackets and separated by commas.
     *
     * @return a string representation of this tuple
     */
    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    /**
     * Compares this tuple to the specified object for equality.
     * Two tuples are considered equal if they have the same elements in the same
     * order.
     *
     * @param o the object to compare with
     * @return {@code true} if the specified object is equal to this tuple,
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || !(o instanceof Tuple))
            return false;
        Tuple tuple = (Tuple) o;
        return Arrays.equals(elements, tuple.elements);
    }

    /**
     * Returns a hash code value for this tuple.
     *
     * @return a hash code value for this tuple
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }
}
