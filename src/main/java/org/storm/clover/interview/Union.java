package org.storm.clover.interview;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Union {
    public static <T> Collection<T> union(Collection<? extends T> col1, Collection<? extends T> col2) {
        Set<T> union = new HashSet<>();
        union.addAll(col1);
        union.addAll(col2);
        return union;
    }

    public static <T> Collection<T> unionLambda(Collection<? extends T> col1, Collection<? extends T> col2) {
        return Stream.concat(col1.stream(), col2.stream()).collect(Collectors.toSet());
    }
}
