package edu.Task3Test;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import edu.Task3.DateParserChain;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateParserChainTest {

    @Test
    public void testParseValidDate() {
        DateParserChain dateParserChain = new DateParserChain();

        String[] dateStrings = {
            "2020-10-10",
            "1/3/1976",
            "tomorrow",
            "2 days ago"
        };

        for (String dateString : dateStrings) {
            Optional<LocalDate> parsedDate = dateParserChain.parseDate(dateString);
            assertTrue(parsedDate.isPresent());
        }
    }

    @Test
    public void testParseInvalidDate() {
        DateParserChain dateParserChain = new DateParserChain();

        String invalidDateString = "invalid-date";
        Optional<LocalDate> parsedDate = dateParserChain.parseDate(invalidDateString);
        assertFalse(parsedDate.isPresent());
    }
}

