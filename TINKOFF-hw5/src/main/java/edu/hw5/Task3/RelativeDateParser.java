package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

@SuppressWarnings("ReturnCount")
public class RelativeDateParser implements DateParser {
    private DateParser nextParser;

    @Override
    public Optional<LocalDate> parse(String input) {
        LocalDate currentDate = LocalDate.now();
        switch (input) {
            case "tomorrow":
                return Optional.of(currentDate.plusDays(1));
            case "today":
                return Optional.of(currentDate);
            case "yesterday":
                return Optional.of(currentDate.minusDays(1));
            default:
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


