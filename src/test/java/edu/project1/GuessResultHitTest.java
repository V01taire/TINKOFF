package edu.project1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GuessResultHitTest {

    @Test
    void hitMessageIsCorrect() {
        char[] state = "example".toCharArray();
        GuessResult result = new GuessResult.Hit(state);
        assertEquals("Hit!", result.message());
    }

    @Test
    void hitStateReturnsCorrectState() {
        char[] state = "example".toCharArray();
        GuessResult result = new GuessResult.Hit(state);
        assertArrayEquals(state, result.state());
    }
}
