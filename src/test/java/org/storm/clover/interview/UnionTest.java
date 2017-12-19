package org.storm.clover.interview;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UnionTest {
    Collection<String> col1, col2;

    @Before
    public void setUp() throws Exception {
        col1 = new HashSet<String>();
        col1.add("Z");
        col1.add("Z");
        col1.add("Y");
        col1.add("X");

        col2 = new HashSet<String>();
        col2.add("Z");
        col2.add("U");
        col2.add("U");
        col2.add("L");
    }

    @Test
    public void union() throws Exception {
        Union union = new Union();
        Collection<String> actual = union.union(col1, col2);

        assertNotNull(actual);
        Assert.assertEquals(5, actual.size());
        assertTrue(actual.contains("Z"));
        assertTrue(actual.contains("Y"));
        assertTrue(actual.contains("X"));
        assertTrue(actual.contains("U"));
        assertTrue(actual.contains("L"));
    }

    @Test
    public void unionLambda() throws Exception {
        Union union = new Union();
        Collection<String> actual = union.unionLambda(col1, col2);

        assertNotNull(actual);
        Assert.assertEquals(5, actual.size());
        assertTrue(actual.contains("Z"));
        assertTrue(actual.contains("Y"));
        assertTrue(actual.contains("X"));
        assertTrue(actual.contains("U"));
        assertTrue(actual.contains("L"));
    }
}