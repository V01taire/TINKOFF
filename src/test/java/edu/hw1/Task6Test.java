package edu.hw1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task6Test {

    @ParameterizedTest
    @CsvSource({
            "3524, 3",
            "5432, 3",
            "6621, 5",
            "6554, 4",
            "1234, 3"
    })
    void countKTest(int input, int expected) {
        assertEquals(expected, Task6.countK(input));
    }
}
