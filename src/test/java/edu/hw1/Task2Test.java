package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    @Test
    public void countDigitsTest() {
        Task2 task = new Task2();

        // Проверяем случаи с разными числами
        assertEquals(4, task.countDigits(4666));
        assertEquals(3, task.countDigits(544));
        assertEquals(1, task.countDigits(0));

        // Проверяем случаи с отрицательными числами
        assertEquals(5, task.countDigits(-12345));
        assertEquals(10, task.countDigits(-1234567890));
    }
}
