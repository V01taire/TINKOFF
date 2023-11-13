package edu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {

    @Test
    @DisplayName("Валидация российских номерных знаков")
    void isTheCarNumberValidForRussianFederation() {
        // given
        String[] validNumbers = {"А123ВЕ777", "О777ОО177"};
        String[] invalidNumbers = {"123АВЕ777", "А123ВГ77", "А123ВЕ7777"};

        // when & then
        assertThat(Task5.isTheCarNumberValidForRussianFederation(validNumbers[0])).isTrue();  // Валидный номер
        assertThat(Task5.isTheCarNumberValidForRussianFederation(validNumbers[1])).isTrue();  // Валидный номер

        assertThat(Task5.isTheCarNumberValidForRussianFederation(invalidNumbers[0])).isFalse();  // Невалидный номер
        assertThat(Task5.isTheCarNumberValidForRussianFederation(invalidNumbers[1])).isFalse();  // Невалидный номер
        assertThat(Task5.isTheCarNumberValidForRussianFederation(invalidNumbers[2])).isFalse();  // Невалидный номер
    }
}

