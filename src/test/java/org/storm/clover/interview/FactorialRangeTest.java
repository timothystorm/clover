package org.storm.clover.interview;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FactorialRangeTest {
    @Test
    public void factorial() {
        FactorialRange factorial = new FactorialRange();
        assertEquals(60, factorial.factorial(3, 5));
        assertEquals(840, factorial.factorial(4, 7));
    }
}