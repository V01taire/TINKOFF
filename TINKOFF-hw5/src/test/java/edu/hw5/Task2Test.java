package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @Test
    @DisplayName("Поиск пятниц 13-го числа для 1925 года")
    void findFridayThe13thsFor1925() {
        // given
        int year = 1925;

        // when
        String result = Task2.findFridayThe13ths(year);

        // then
        assertThat(result).isEqualTo("[1925-02-13, 1925-03-13, 1925-11-13]");
    }

    @Test
    @DisplayName("Поиск пятниц 13-го числа для 2024 года")
    void findFridayThe13thsFor2024() {
        // given
        int year = 2024;

        // when
        String result = Task2.findFridayThe13ths(year);

        // then
        assertThat(result).isEqualTo("[2024-09-13, 2024-12-13]");
    }

    @Test
    @DisplayName("Поиск следующей пятницы 13-го числа после 2023-05-20")
    void findNextFriday13thAfter2023_05_20() {
        // given
        LocalDate dateToCheck = LocalDate.of(2023, 5, 20);

        // when
        LocalDate result = Task2.findNextFriday13th(dateToCheck);

        // then
        assertThat(result).isEqualTo(LocalDate.of(2023, 10, 13));
    }
}
