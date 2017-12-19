package org.storm.clover.algorithm;

/**
 * Recursively calculate factorials [x!]
 */
public class Factorial {
    public static long factorial(long n) {
        assert (n >= 0) && (n <= 20) : "n out of range [0-20]";
        if (n <= 1) return n;
        return n * factorial(n - 1);
    }
}
