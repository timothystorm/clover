package org.storm.clover.interview;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.*;

public class IntersectTest {
    Collection<String> col1, col2;

    @Before
    public void setUp() throws Exception {
        col1 = new HashSet<String>();
        col1.add("A");
        col1.add("B");
        col1.add("C");
        col1.add("D");

        col2 = new HashSet<String>();
        col2.add("A");
        col2.add("A");
        col2.add("B");
        col2.add("X");
        col2.add("Z");
    }

    @Test
    public void intersect() throws Exception {
        Intersect intersection = new Intersect();
        Collection<String> actual = intersection.intersect(col1, col2);

        assertNotNull(actual);
        assertEquals(2, actual.size());
        assertTrue(actual.contains("A"));
        assertTrue(actual.contains("B"));
        assertFalse(actual.contains("C"));
        assertFalse(actual.contains("D"));
        assertFalse(actual.contains("X"));
        assertFalse(actual.contains("Z"));
    }

    @Test
    public void intersectLambda() throws Exception {
        Intersect intersection = new Intersect();
        Collection<String> actual = intersection.intersectLambda(col1, col2);

        assertNotNull(actual);
        assertEquals(2, actual.size());
        assertTrue(actual.contains("A"));
        assertTrue(actual.contains("B"));
        assertFalse(actual.contains("C"));
        assertFalse(actual.contains("D"));
        assertFalse(actual.contains("X"));
        assertFalse(actual.contains("Z"));
    }
}