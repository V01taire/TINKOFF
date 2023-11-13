package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {

    @Test
    @DisplayName("Проверка нечетной длины")
    void isOddLength() {
        // given
        String testString = "1010";

        // when & then
        assertThat(Task8.isOddLength(testString)).isFalse();  // Нечетной длины
    }

    @Test
    @DisplayName("Проверка начала с 0 и нечетной длины, или начала с 1 и четной длины")
    void startsWith0AndHasOddLengthOrStartsWith1AndHasEvenLength() {
        // given
        String testString = "110";

        // when & then
        assertThat(Task8.startsWith0AndHasOddLengthOrStartsWith1AndHasEvenLength(testString)).isFalse();  // Начинается с 0 и имеет нечетную длину
    }

    @Test
    @DisplayName("Проверка количества 0, кратного 3")
    void countOf0IsMultipleOf3() {
        // given
        String testString = "000000111";

        // when & then
        assertThat(Task8.countOf0IsMultipleOf3(testString)).isTrue();  // Количество 0 кратно 3
    }

    @Test
    @DisplayName("Проверка любой строки, кроме 11 или 111")
    void anyStringExcept11Or111() {
        // given
        String testString = "1110";

        // when & then
        assertThat(Task8.anyStringExcept11Or111(testString)).isTrue();  // Любая строка, кроме 11 или 111
    }

    @Test
    @DisplayName("Проверка каждого нечетного символа, равного 1")
    void everyOddCharacterIs1() {
        // given
        String testString = "100010";

        // when & then
        assertThat(Task8.everyOddCharacterIs1(testString)).isFalse();  // Каждый нечетный символ равен 1
    }

    @Test
    @DisplayName("Проверка содержания не менее двух 0 и не более одной 1")
    void atLeastTwo0AndAtMostOne1() {
        // given
        String testString = "10011";

        // when & then
        assertThat(Task8.atLeastTwo0AndAtMostOne1(testString)).isFalse();  // Содержит не менее двух 0 и не более одной 1
    }

    @Test
    @DisplayName("Проверка отсутствия последовательных 1")
    void noConsecutive1() {
        // given
        String testString = "001010101";

        // when & then
        assertThat(Task8.noConsecutive1(testString)).isTrue();  // Нет последовательных 1
    }
}
