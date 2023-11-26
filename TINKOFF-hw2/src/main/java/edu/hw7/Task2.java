package edu.hw7.Task2;

import java.util.stream.LongStream;

public class Task2 {

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The factorial is only defined for non-negative numbers");
        }

        return LongStream.rangeClosed(1, n)
            .parallel()  // Используем parallelStream для распараллеливания вычислений
            .reduce(1, (a, b) -> a * b);
    }
}

