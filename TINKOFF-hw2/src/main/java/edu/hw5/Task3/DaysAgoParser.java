package edu.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class DaysAgoParser implements DateParser {
    @Override
    public Optional<LocalDate> parse(String input) {
        try {
            int daysAgo = Integer.parseInt(input.split(" ")[0]);
            return Optional.of(LocalDate.now().minusDays(daysAgo));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void setNextParser(DateParser nextParser) {
        // Этот парсер не имеет следующего парсера
    }
}

