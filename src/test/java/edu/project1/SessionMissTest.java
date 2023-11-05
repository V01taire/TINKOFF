package edu.project1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SessionMissTest {

    @Test
    void missResultWhenGuessingIncorrectCharacter() {
        Session session = new Session("word", 5);
        GuessResult result = session.guess('x');

        assertTrue(result instanceof GuessResult.Miss);
        assertEquals("____", new String(result.state()));
        assertEquals("Miss!", result.message());
    }
}
