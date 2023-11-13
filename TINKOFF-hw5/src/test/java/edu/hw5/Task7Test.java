package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {

    @Test
    @DisplayName("Проверка содержания не менее 3 символов, где третий символ равен 0")
    void containsAtLeast3Zeros() {
        // given
        String testString = "010101";

        // when & then
        assertThat(Task7.containsAtLeast3Zeros(testString)).isTrue();  // Содержит не менее 3 символов, где третий символ - 0
    }

    @Test
    @DisplayName("Проверка начала и окончания строки одним и тем же символом")
    void startsAndEndsWithSameChar() {
        // given
        String testString = "101";

        // when & then
        assertThat(Task7.startsAndEndsWithSameChar(testString)).isTrue();  // Начинается и заканчивается одним и тем же символом
    }

    @Test
    @DisplayName("Проверка длины строки от 1 до 3 символов")
    void lengthBetween1And3() {
        // given
        String testString = "001";

        // when & then
        assertThat(Task7.lengthBetween1And3(testString)).isTrue();  // Длина не менее 1 и не более 3 символов
    }
}
