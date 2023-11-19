package edu.project3.Parsers;

import static org.assertj.core.api.Assertions.assertThat;
import edu.project3.LogFiles.LogAnalyzerStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;

public class URL_ParserTest {

    private static final LocalDate MIN_DATE = LocalDate.MIN;
    private static final LocalDate MAX_DATE = LocalDate.MAX;

    private URL_Parser urlParser;
    private LogAnalyzerStorage storage;

    @BeforeEach
    void setUp() {
        storage = new LogAnalyzerStorage();
    }

    @Nested
    @DisplayName("readData method")
    class ReadData {

        @Test
        @DisplayName("Reading data from a valid URL")
        void readDataFromValidURL() throws IOException, URISyntaxException {
            // given
            URL url = new URL(
                "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs");
            urlParser = new URL_Parser(url.toString(), storage, MIN_DATE, MAX_DATE);

            // when
            urlParser.readData();

            // then
            assertThat(storage.getFiles()).containsExactly(url.getFile());
        }
    }
}

