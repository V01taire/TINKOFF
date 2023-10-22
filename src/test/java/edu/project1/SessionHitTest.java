package edu.project1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SessionHitTest {

    @Test
    void hitResultWhenGuessingCorrectCharacter() {
        Session session = new Session("word", 5);
        GuessResult result = session.guess('w');

        assertTrue(result instanceof GuessResult.Hit);
        assertEquals("w___", new String(result.state()));
        assertEquals("Hit!", result.message());
    }
}

