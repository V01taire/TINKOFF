package edu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    @Test
    @DisplayName("Проверка наличия специальных символов в пароле")
    void isValidPassword() {
        // given
        String[] passwords = {"password123", "securePassword!", "noSpecialChars"};

        // when & then
        assertThat(Task4.isValidPassword(passwords[0])).isFalse(); // Нет специальных символов
        assertThat(Task4.isValidPassword(passwords[1])).isTrue();  // Содержит специальный символ "!"
        assertThat(Task4.isValidPassword(passwords[2])).isFalse(); // Нет специальных символов
    }
}
