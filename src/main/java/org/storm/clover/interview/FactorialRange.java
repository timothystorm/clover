package org.storm.clover.interview;

/**
 * Create a factorial with a custom range
 */
public class FactorialRange {
    public long factorial(long start, long endInclusive) {
        if (start >= endInclusive) return endInclusive;
        return start * factorial(start + 1, endInclusive);
    }
}
