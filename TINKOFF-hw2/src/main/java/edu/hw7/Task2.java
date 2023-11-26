package edu.hw7;

import java.util.stream.LongStream;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task2 {

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The factorial is only defined for non-negative numbers");
        }

        return LongStream.rangeClosed(1, n)
            .parallel()
            .reduce(1, (a, b) -> a * b);
    }
}

