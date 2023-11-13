package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public interface DateParser {

    Optional<LocalDate> parse(String input);

    void setNextParser(DateParser nextParser);
}
