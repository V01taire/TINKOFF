package edu.Task3Test;

import java.time.LocalDate;
import java.util.Optional;

import edu.Task3.DaysAgoParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DaysAgoParserTest {

    @Test
    public void testParseValidDaysAgo() {
        DaysAgoParser parser = new DaysAgoParser();

        int daysAgo = 5;
        Optional<LocalDate> result = parser.parse(daysAgo + " days ago");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.now().minusDays(daysAgo), result.get());

        daysAgo = 1;
        result = parser.parse(daysAgo + " day ago");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.now().minusDays(daysAgo), result.get());
    }

    @Test
    public void testParseInvalidDaysAgo() {
        DaysAgoParser parser = new DaysAgoParser();

        Optional<LocalDate> result = parser.parse("invalid format");
        assertFalse(result.isPresent());

        result = parser.parse("0 days ago");
        assertTrue(result.isPresent());

        result = parser.parse("-1 days ago");
        assertTrue(result.isPresent());
    }
}

