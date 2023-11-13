package edu.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class DateFormatParser implements DateParser {
    private DateParser nextParser;

    @Override
    public Optional<LocalDate> parse(String input) {
        try {
            return Optional.of(LocalDate.parse(input));
        } catch (Exception e) {
            if (nextParser != null) {
                return nextParser.parse(input);
            }
            return Optional.empty();
        }
    }

    @Override
    public void setNextParser(DateParser nextParser) {
        this.nextParser = nextParser;
    }
}

