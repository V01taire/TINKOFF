package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    private final Task4 task4 = new Task4();

    @ParameterizedTest
    @CsvSource({
        "123456, 214365",
        "hTsii  s aimex dpus rtni.g, This is a mixed up string.",
        "badce, abcde",
        "a, a",       // строка из одного символа не должна измениться
        "'', ''"       // пустая строка не должна измениться
    })
    void fixStringTest(String input, String expectedOutput) {
        assertEquals(expectedOutput, task4.fixString(input));
    }
}
