package edu.hw7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task2Test {

    @Test
    void testFactorialWithPositiveNumber() {
        int n = 5;
        long result = Task2.factorial(n);
        assertEquals(120, result);
    }

    @Test
    void testFactorialWithZero() {
        int n = 0;
        long result = Task2.factorial(n);
        assertEquals(1, result);
    }

    @Test
    void testFactorialWithNegativeNumber() {
        int n = -5;
        assertThrows(IllegalArgumentException.class, () -> Task2.factorial(n));
    }

    @Test
    void testFactorialWithLargeNumber() {
        int n = 20;

        long result = Task2.factorial(n);

        assertFalse(result < 0, "Expected result to be negative due to long overflow, but it was: " + result);
    }
}


