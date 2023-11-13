package edu.hw5.Task3Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import edu.hw5.Task3.DateFormatParser;
import org.junit.jupiter.api.Test;

public class DateFormatParserTest {

    @Test
    public void testParseValidDate() {
        DateFormatParser parser = new DateFormatParser();
        Optional<LocalDate> result = parser.parse("2020-10-10");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(2020, 10, 10), result.get());
    }

    @Test
    public void testParseInvalidDate() {
        DateFormatParser parser = new DateFormatParser();
        Optional<LocalDate> result = parser.parse("invalid-date");
        assertFalse(result.isPresent());
    }

    @Test
    public void testParseWithNextParser() {
        DateFormatParser parser1 = new DateFormatParser();
        DateFormatParser parser2 = new DateFormatParser();
        parser1.setNextParser(parser2);

        Optional<LocalDate> result = parser1.parse("invalid-date");

        assertFalse(result.isPresent());
    }

    @Test
    public void testParseWithNextParserSuccess() {
        DateFormatParser parser1 = new DateFormatParser();
        DateFormatParser parser2 = new DateFormatParser();
        parser1.setNextParser(parser2);

        Optional<LocalDate> result = parser1.parse("2020-10-10");

        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(2020, 10, 10), result.get());
    }

    @Test
    public void testParseWithMultipleNextParsers() {
        DateFormatParser parser1 = new DateFormatParser();
        DateFormatParser parser2 = new DateFormatParser();
        DateFormatParser parser3 = new DateFormatParser();
        parser1.setNextParser(parser2);
        parser2.setNextParser(parser3);

        Optional<LocalDate> result = parser1.parse("invalid-date");

        assertFalse(result.isPresent());
    }

    @Test
    public void testParseWithMultipleNextParsersSuccess() {
        DateFormatParser parser1 = new DateFormatParser();
        DateFormatParser parser2 = new DateFormatParser();
        DateFormatParser parser3 = new DateFormatParser();
        parser1.setNextParser(parser2);
        parser2.setNextParser(parser3);

        Optional<LocalDate> result = parser1.parse("2020-10-10");

        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(2020, 10, 10), result.get());
    }
}
