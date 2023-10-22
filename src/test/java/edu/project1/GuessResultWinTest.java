package edu.project1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GuessResultWinTest {

    @Test
    void winStateReturnsCorrectState() {
        char[] state = "example".toCharArray();
        int attempts = 5;
        GuessResult result = new GuessResult.Win(state, attempts);
        assertArrayEquals(state, result.state());
    }
}
