package edu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    private static final Logger logger = LogManager.getLogger(Task1Test.class);

    @Test
    @DisplayName("Расчет среднего времени за сеанс")
    void calculateTheAverageTimePerSession() {
        // given
        List<String> timestamps = Arrays.asList(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        );

        // when
        String averageTime = Task1.calculateTheAverageTimePerSession(timestamps);

        // then
        assertThat(averageTime).isEqualTo("3ч 40м");
        logger.info("Среднее время за сеанс: {}", averageTime);
    }

    @Test
    @DisplayName("Обработка случая с отсутствием данных")
    void handleEmptyTimestamps() {
        // given
        List<String> timestamps = List.of();

        // when
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            Task1.calculateTheAverageTimePerSession(timestamps);
        });

        // then
        assertThat(exception.getMessage()).isEqualTo("Cannot divide by zero");
        logger.info("Обработка случая с отсутствием данных: {}", exception.getMessage());
    }

    @Test
    @DisplayName("Обработка случая с одним сеансом")
    void handleSingleSession() {
        // given
        List<String> timestamps = Arrays.asList(
            "2022-03-12, 20:20 - 2022-03-12, 23:50"
        );

        // when
        String averageTime = Task1.calculateTheAverageTimePerSession(timestamps);

        // then
        assertThat(averageTime).isEqualTo("3ч 30м");
        logger.info("Среднее время за сеанс (один сеанс): {}", averageTime);
    }

    @Test
    @DisplayName("Обработка случая с одинаковыми сеансами")
    void handleEqualSessions() {
        // given
        List<String> timestamps = Arrays.asList(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-03-12, 20:20 - 2022-03-12, 23:50"
        );

        // when
        String averageTime = Task1.calculateTheAverageTimePerSession(timestamps);

        // then
        assertThat(averageTime).isEqualTo("3ч 30м");
        logger.info("Среднее время за сеанс (одинаковые сеансы): {}", averageTime);
    }
}

