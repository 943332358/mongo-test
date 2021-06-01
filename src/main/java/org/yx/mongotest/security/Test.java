package org.yx.mongotest.security;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) {
        IntStream.rangeClosed(5, 10)
                .filter(f -> f > 7)
                .boxed()
                .collect(Collectors.toList());
    }
}
