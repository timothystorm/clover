package org.storm.clover.euler;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Problem_02Test {

    @Test
    public void fibonacciEvenSum() {
        assertEquals(44, new Problem_02().fibonacciEvenSum(50));
        assertEquals(4613732, new Problem_02().fibonacciEvenSum(4_000_000));
    }

    @Test
    public void fibonacciProof() {
        assertEquals(44, new Problem_02().fibonacciProof(50));
        assertEquals(4613732, new Problem_02().fibonacciProof(4_000_000));
    }

    @Test
    public void fibonacci() {
        long limit = 2_000_000_000_000_000_000L;
        assertEquals(new Problem_02().fibonacciEvenSum(limit), new Problem_02().fibonacciProof(limit));
    }
}