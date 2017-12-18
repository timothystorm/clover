package org.storm.clover.algorithm;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CircularIteratorTest {
    @Test
    public void next() {
        CircularIterator<Integer> countOff = CircularIterator.intRange(1, 10);
        int cycles = countOff.length() * 3;
        for (int i = 0; i < cycles; i++) {
            assertThat(countOff.next(), both(greaterThan(0)).and(lessThan(11)));
        }
    }
}