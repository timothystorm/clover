package org.storm.clover.interview;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * create a utility to join 2 collections where they intersect
 */
public class Intersect {
    public static <T> Collection<T> intersect(final Collection<? extends T> col1, final Collection<? extends T> col2) {
        Set<T> intersect = new HashSet<>();
        intersect.addAll(col1);
        intersect.retainAll(col2);
        return intersect;
    }

    public static <T> Collection<T> intersectLambda(final Collection<? extends T> col1, final Collection<? extends T> col2) {
        return col1.stream().filter(col2::contains).collect(Collectors.toSet());
    }
}
