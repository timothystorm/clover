package org.storm.clover.euler;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Problem_02Test {

    @Test
    public void fibonacciEvenSum_example() throws Exception {
        assertEquals(44, new Problem_02().fibonacciEvenSum(50));
    }

    @Test
    public void fibonacciEvenSum_problem() throws Exception {
        assertEquals(4613732, new Problem_02().fibonacciEvenSum(4_000_000));
    }

}