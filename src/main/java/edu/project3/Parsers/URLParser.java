package edu.project3.Parsers;

import edu.project3.LogFiles.LogAnalyzerStorage;
import edu.project3.Interfaces.Parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URL_Parser implements Parser {

    private final URL url;
    private final LogAnalyzerStorage storage;
    private final LocalDate startDate;
    private final LocalDate endDate;

    private static final Pattern LOG_ENTRY_PATTERN = Pattern.compile(
        "^(.*)"
            + "\\s-\\s.*\\s"
            + "\\[(.*?):.*]\\s"
            + "\"(.*)\\s(.*)\\s.*\"\\s"
            + "(\\d{3})\\s"
            + "(\\d{1,15})"
            + "\\s\".*\"\\s\".*\"$"
    );

    public URL_Parser(String urlString, LogAnalyzerStorage storage, LocalDate startDate, LocalDate endDate)
        throws URISyntaxException, MalformedURLException {

        this.url = new URI(urlString).toURL();
        this.storage = storage;
        this.startDate = startDate == null ? LocalDate.MIN : startDate;
        this.endDate = endDate == null ? LocalDate.MAX : endDate;
    }

    private void processLogEntry(Matcher matcher) {
        LocalDate date = LocalDate.parse(
            matcher.group(2), DateTimeFormatter.ofPattern("dd/MMM/yyyy", Locale.ENGLISH)
        );

        if ((startDate.isBefore(date) || startDate.isEqual(date)) && (endDate.isAfter(date) || endDate.isEqual(date))) {
            storage.incrementRequestCount();
            storage.incrementAddressCount(matcher.group(1));
            storage.incrementDateCount(date);
            storage.incrementResourceCount(matcher.group(4));
            storage.incrementAnswerCodeCount(matcher.group(5));
            storage.addRequestSize(Long.parseLong(matcher.group(6)));
        }
    }

    @Override
    public void readData() throws IOException {
        storage.setFiles(List.of(url.getFile()));

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()))) {
            reader.lines().forEach(line -> {
                Matcher matcher = LOG_ENTRY_PATTERN.matcher(line);
                if (matcher.find()) {
                    processLogEntry(matcher);
                }
            });
        }
    }
}

