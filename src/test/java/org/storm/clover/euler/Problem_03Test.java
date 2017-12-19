package org.storm.clover.euler;

import org.junit.Test;

import java.util.Set;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class Problem_03Test {

    @Test
    public void primeFactorsOf_example() throws Exception {
        Set<Long> primes = Problem_03.primeFactorsOf(13195);
        assertNotNull(primes);
        assertThat(primes, containsInAnyOrder(5L, 7L, 13L, 29L));
    }

    @Test
    public void largestPrimeFactorOf_example() throws Exception {
        assertEquals(29L, Problem_03.largestPrimeFactorOf(13195));
    }

    @Test
    public void primeFactorOf_problem() throws Exception {
       assertEquals(6857L, Problem_03.largestPrimeFactorOf(600851475143L));
    }
}