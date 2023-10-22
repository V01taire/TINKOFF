package edu.project1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SessionDefeatTest {

    @Test
    void defeatResultAfterExceedingMaxAttempts() {
        Session session = new Session("word", 3);
        GuessResult result = session.guess('x');
        result = session.guess('y');
        result = session.guess('z');

        assertTrue(result instanceof GuessResult.Defeat);
        assertEquals("word", new String(result.state()));
        assertEquals("You lost! The correct word was: word", result.message());
    }
}
