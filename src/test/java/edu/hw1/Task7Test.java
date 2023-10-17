package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task7Test {
    private final Task7 bitwiseOperations = new Task7();

    @ParameterizedTest
    @CsvSource({"16, 1, 1", "17, 2, 6"})
    public void rotateLeftTest(int n, int shift, int expected) {
        int result = bitwiseOperations.rotateLeft(n, shift);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"8, 1, 4"})
    public void rotateRightTest(int n, int shift, int expected) {
        int result = bitwiseOperations.rotateRight(n, shift);
        assertEquals(expected, result);
    }
}


