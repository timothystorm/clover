package org.storm.clover.euler;

/**
 * Each new term in the Fibonacci sequence is generated by adding the previous two terms. By starting with 1 and 2, the first 10 terms will be:
 * 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
 * <p>
 * By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.
 * <p>
 * Answer: 4613732
 * Completed on Sun, 10 Dec 2017, 17:44
 * <p>
 * <a href="https://en.wikipedia.org/wiki/Glossary_of_climbing_terms#flash">Flash'ed</a> !!
 *
 * @TODO: functional'ize
 */
public class Problem_02 {
    public long fibonacciEvenSum(long limit) {
        return fibonacciEvenSum(1, limit);
    }

    public long fibonacciEvenSum(long startAt, long maxInclusive) {
        long first = startAt, second = startAt, third = 0L, sum = 0L;

        do {
            // calculate
            if (third % 2 == 0) sum += third;

            // progress
            first = second;
            second = third;
            third = first + second;
        } while (third < maxInclusive);
        return sum;
    }

    public long fibonacciProof(long maxInclusive) {
        return fibonacciProof(1, maxInclusive);
    }

    /**
     * It is easy to prove that every third Fibonacci number is even.  Because this method takes larger progress steps
     * it can overflow sooner than {@link #fibonacciEvenSum(long, long)}.
     *
     * @param startAt
     * @param maxInclusive
     * @return
     */
    public long fibonacciProof(long startAt, long maxInclusive) {
        long first = startAt, second = startAt, third = 0L, sum = 0L;

        while (third < maxInclusive) {
            // calculate
            sum += third;

            // progress
            first = (second + third);
            second = (third + first);
            third = (first + second);
        }
        return sum;
    }
}
