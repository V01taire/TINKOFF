package edu.project1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GuessResultMissTest {

    @Test
    void missMessageIsCorrect() {
        char[] state = "example".toCharArray();
        GuessResult result = new GuessResult.Miss(state);
        assertEquals("Miss!", result.message());
    }

    @Test
    void missStateReturnsCorrectState() {
        char[] state = "example".toCharArray();
        GuessResult result = new GuessResult.Miss(state);
        assertArrayEquals(state, result.state());
    }
}
