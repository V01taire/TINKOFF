package edu.hw3;

import edu.hw3.Task4.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task4Test {

    @Test
    @DisplayName("Тест преобразования арабских чисел в римские")
    void testConvertToRoman() {
        // given
        int[] arabicNumbers = {1, 4, 9, 27, 49, 50, 73, 89, 100, 500, 999, 1000, 1984, 2022};
        String[] expectedRomanNumbers = {"I", "IV", "IX", "XXVII", "XLIX", "L", "LXXIII", "LXXXIX", "C", "D", "CMXCIX", "M", "MCMLXXXIV", "MMXXII"};

        // when
        String[] actualRomanNumbers = new String[arabicNumbers.length];
        for (int i = 0; i < arabicNumbers.length; i++) {
            actualRomanNumbers[i] = Task4.convertToRoman(arabicNumbers[i]);
        }

        // then
        assertArrayEquals(expectedRomanNumbers, actualRomanNumbers);
    }

    @Test
    @DisplayName("Тест преобразования числа 0 в римское число")
    void testConvertZeroToRoman() {
        // given
        int number = 0;

        // when
        String romanNumber = Task4.convertToRoman(number);

        // then
        assertEquals("", romanNumber);
    }


    @Test
    @DisplayName("Тест преобразования максимального возможного числа в римское число")
    void testConvertMaxNumberToRoman() {
        // given
        int maxNumber = 3999;

        // when
        String romanNumber = Task4.convertToRoman(maxNumber);

        // then
        assertEquals("MMMCMXCIX", romanNumber);
    }
}

