package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Task5Test {

    @ParameterizedTest
    @CsvSource({
        "11211230, true",
        "13001120, true",
        "23336014, true",
        "11, true",
        "12345, false",
    })
    void isPalindromeDescendantTest(int input, boolean expected) {
        assertEquals(expected, Task5.isPalindromeDescendant(input));
    }
}
