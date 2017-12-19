package org.storm.clover.algorithm;

import java.util.stream.IntStream;

/**
 * Iterates a set of elements forever
 *
 * @param <T>
 */
public class CircularIterator<T> {
    private final T[] _elements;
    private final int _length;
    private int _index = 0;


    public CircularIterator(T... elements) {
        _length = (_elements = elements).length;
    }

    public static CircularIterator<Integer> intRange(int startInclusive, int endInclusive) {
        Integer[] range = IntStream.rangeClosed(startInclusive, endInclusive).boxed().toArray(Integer[]::new);
        return new CircularIterator<>(range);
    }


    /**
     * @return next element in circular sequence
     */
    public T next() {
        T e = _elements[_index];
        _index = (_index + 1) % _elements.length;
        return e;
    }

    public int length() {
        return _length;
    }
}
