package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class DateParserChain {

    private DateParser parserChain;

    public DateParserChain() {
        buildChain();
    }

    private void buildChain() {
        parserChain = new DateFormatParser();
        DateParser dayMonthYearParser = new DayMonthYearParser();
        DateParser relativeDateParser = new RelativeDateParser();
        DateParser daysAgoParser = new DaysAgoParser();

        parserChain.setNextParser(dayMonthYearParser);
        dayMonthYearParser.setNextParser(relativeDateParser);
        relativeDateParser.setNextParser(daysAgoParser);
    }

    public Optional<LocalDate> parseDate(String input) {
        return parserChain.parse(input);
    }
}




