package edu.Task3Test;

import java.time.LocalDate;
import java.util.Optional;

import edu.Task3.RelativeDateParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class RelativeDateParserTest {

    @Test
    public void testParseTomorrow() {
        RelativeDateParser parser = new RelativeDateParser();
        Optional<LocalDate> result = parser.parse("tomorrow");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.now().plusDays(1), result.get());
    }

    @Test
    public void testParseToday() {
        RelativeDateParser parser = new RelativeDateParser();
        Optional<LocalDate> result = parser.parse("today");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.now(), result.get());
    }

    @Test
    public void testParseYesterday() {
        RelativeDateParser parser = new RelativeDateParser();
        Optional<LocalDate> result = parser.parse("yesterday");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.now().minusDays(1), result.get());
    }

    @Test
    public void testParseInvalidDate() {
        RelativeDateParser parser = new RelativeDateParser();
        Optional<LocalDate> result = parser.parse("invalid-date");
        assertFalse(result.isPresent());
    }
}
