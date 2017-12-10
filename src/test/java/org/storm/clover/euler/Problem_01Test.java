package org.storm.clover.euler;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Problem_01Test {
    @Test
    public void sum_lamda() {
        Problem_01 example = new Problem_01(10, 3, 5);
        assertEquals(23L, example.sum_lambda());

        Problem_01 problem = new Problem_01(1000, 3, 5);
        assertEquals(233168L, problem.sum_lambda());
    }

    @Test
    public void sum_mixed() {
        Problem_01 example = new Problem_01(10, 3, 5);
        assertEquals(23L, example.sum_mixed());

        Problem_01 problem = new Problem_01(1000, 3, 5);
        assertEquals(233168L, problem.sum_mixed());
    }

    @Test
    public void sum_trad() {
        Problem_01 example = new Problem_01(10, 3, 5);
        assertEquals(23L, example.sum_trad());

        Problem_01 problem = new Problem_01(1000, 3, 5);
        assertEquals(233168L, problem.sum_trad());
    }
}