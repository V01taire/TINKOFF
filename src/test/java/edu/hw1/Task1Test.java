package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @ParameterizedTest
    @CsvSource({
            "01:00, 60",
            "13:56, 836",
            "10:60, -1",
            "12:345, -1",
            "123, -1",
            "-01:30, -1",
            "01:-30, -1",
            "-01:-30, -1"
    })
    void convertToSecondsTest(String input, int expected) {
        Task1 videoLength = new Task1();
        int result = videoLength.convertToSeconds(input);
        assertEquals(expected, result);
    }
}
