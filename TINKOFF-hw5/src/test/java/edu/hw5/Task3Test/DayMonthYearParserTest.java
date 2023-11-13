package edu.hw5.Task3Test;

import java.time.LocalDate;
import java.util.Optional;

import edu.hw5.Task3.DayMonthYearParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DayMonthYearParserTest {

    @Test
    public void testParseValidDate() {
        DayMonthYearParser parser = new DayMonthYearParser();
        Optional<LocalDate> result = parser.parse("1/3/1976");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(1976, 3, 1), result.get());
    }

    @Test
    public void testParseValidDateSingleDigitMonthDay() {
        DayMonthYearParser parser = new DayMonthYearParser();
        Optional<LocalDate> result = parser.parse("1/1/2000");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(2000, 1, 1), result.get());
    }

    @Test
    public void testParseInvalidDate() {
        DayMonthYearParser parser = new DayMonthYearParser();
        Optional<LocalDate> result = parser.parse("invalid-date");
        assertFalse(result.isPresent());
    }

    @Test
    public void testParseInvalidDateSingleDigitMonth() {
        DayMonthYearParser parser = new DayMonthYearParser();
        Optional<LocalDate> result = parser.parse("5/20/2022");
        assertFalse(result.isPresent());
    }

    @Test
    public void testParseInvalidDateSingleDigitDay() {
        DayMonthYearParser parser = new DayMonthYearParser();
        Optional<LocalDate> result = parser.parse("10/5/2022");
        assertTrue(result.isPresent());
    }
}

