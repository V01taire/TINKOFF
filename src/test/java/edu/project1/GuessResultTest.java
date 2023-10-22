package edu.project1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GuessResultTest {

    @Test
    @DisplayName("Тест для пустого состояния (все пустые записи)")
    void emptyStateTest() {
        char[] state = {'_', '_', '_', '_'};
        GuessResult result = new GuessResult.Miss(state);

        assertArrayEquals(state, result.state());
        assertEquals("Miss!", result.message());
    }

    @Test
    @DisplayName("Тест для состояния с одной угаданной буквой")
    void singleHitStateTest() {
        char[] state = {'_', 'a', '_', '_'};
        GuessResult result = new GuessResult.Hit(state);

        assertArrayEquals(state, result.state());
        assertEquals("Hit!", result.message());
    }

    @Test
    @DisplayName("Тест для проигрыша при максимальном количестве попыток")
    void defeatAfterMaxAttemptsTest() {
        char[] state = {'a', 'b', 'c'};
        GuessResult result = new GuessResult.Defeat(state);
        assertArrayEquals(state, result.state());
        assertEquals("You lost! The correct word was: abc", result.message());
    }
}
