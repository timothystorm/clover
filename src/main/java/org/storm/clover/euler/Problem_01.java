package org.storm.clover.euler;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.LongStream.range;


/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * <p>
 * Find the sum of all the multiples of 3 or 5 below 1000.
 * <p>
 * Answer: 233168
 * Completed on Sun, 10 Dec 2017, 04:59
 * <p>
 * <p>2 attempts</p>
 */
public class Problem_01 {
    private final Long _end;
    private final Long[] _multiples;

    Problem_01(long end, long... multiples) {
        _end = end;
        _multiples = Arrays.stream(multiples).boxed().toArray(Long[]::new);
    }

    public long sum_lambda() {
        return range(1, _end)
                .filter(i -> Stream.of(_multiples)
                        .anyMatch(m -> (i % m == 0)))
                .sum();
    }

    public long sum_mixed() {
        return range(1, _end)
                .filter(i -> {
                    for (Long m : _multiples) {
                        if (i % m == 0) return true;
                    }
                    return false;
                })
                .sum();
    }

    public long sum_trad() {
        long sum = 0;
        for (int i = 1; i < _end; i++) {
            for (long m : _multiples) {
                if (i % m == 0) {
                    sum += i;
                    break;
                }
            }
        }
        return sum;
    }
}
