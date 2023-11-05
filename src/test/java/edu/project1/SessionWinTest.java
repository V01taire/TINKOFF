package edu.project1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SessionWinTest {

    @Test
    void winResultAfterGuessingAllCharacters() {
        Session session = new Session("word", 5);
        GuessResult result = session.guess('w');
        result = session.guess('o');
        result = session.guess('r');
        result = session.guess('d');

        assertTrue(result instanceof GuessResult.Win);
        assertEquals("word", new String(result.state()));
    }
}
