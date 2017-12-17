package org.storm.clover.euler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * <p>
 * What is the largest prime factor of the number 600851475143 ?
 * <p>
 * <p>
 * Answer: 6857
 * Completed on Sun, 17 Dec 2017, 17:33
 * <p>
 * <p>3 attempts</p>
 */
public class Problem_03 {

    public static Set<Long> primeFactorsOf(final long subject) {
        Set<Long> primeFactors = new HashSet<>();
        long num = subject;

        // divide by 2 to get to an odd term and to reduce test boundary
        if (num % 2 == 0) primeFactors.add(2L);
        while (num % 2 == 0) {
            num /= 2;
        }

        // i must be odd; boundary [(i+2) ; sqrt(num)]
        for (long i = 3; i < Math.sqrt(num); i += 2) {

            if (num % i == 0) primeFactors.add(i);
            while (num % i == 0) {
                num /= i;
            }
        }

        // handle condition where prime factor is greater than 2
        if (num > 2) {
            primeFactors.add(num);
        }

        return primeFactors;
    }

    public static long largestPrimeFactorOf(final long subject) {
        return Collections.max(primeFactorsOf(subject));
    }
}
