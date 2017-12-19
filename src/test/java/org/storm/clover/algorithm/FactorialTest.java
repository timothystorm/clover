package org.storm.clover.algorithm;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class FactorialTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void factorial() {
        assertEquals(1, Factorial.factorial(1));
        assertEquals(2, Factorial.factorial(2));
        assertEquals(6, Factorial.factorial(3));
        assertEquals(24, Factorial.factorial(4));
        assertEquals(120, Factorial.factorial(5));
        //...
        assertEquals(2432902008176640000L, Factorial.factorial(20));
    }

    @Test
    public void factorial_invalid() {
        thrown.expect(AssertionError.class);
        Factorial.factorial(-1);

        thrown.expect(AssertionError.class);
        Factorial.factorial(21);
    }
}