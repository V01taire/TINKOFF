package edu.project1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GuessResultDefeatTest {

    @Test
    void defeatMessageIsCorrect() {
        char[] correctWord = "example".toCharArray();
        GuessResult result = new GuessResult.Defeat(correctWord);
        assertEquals("You lost! The correct word was: example", result.message());
    }

    @Test
    void defeatStateReturnsCorrectState() {
        char[] correctWord = "example".toCharArray();
        GuessResult result = new GuessResult.Defeat(correctWord);
        assertArrayEquals(correctWord, result.state());
    }
}
