package edu.project3.Parsers;

import static org.assertj.core.api.Assertions.assertThatIOException;
import edu.project3.LogFiles.LogAnalyzerStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class FileParserTest {

    private static final LocalDate MIN_DATE = LocalDate.MIN;
    private static final LocalDate MAX_DATE = LocalDate.MAX;

    private FileParser fileParser;
    private LogAnalyzerStorage storage;

    @BeforeEach
    void setUp() {
        storage = new LogAnalyzerStorage();
    }

    @Nested
    @DisplayName("readData method")
    class ReadData {

        @Test
        @DisplayName("Reading data from an empty directory")
        void readDataFromEmptyDirectory() {
            // given
            fileParser = new FileParser("src/test/resources/empty_directory", storage, MIN_DATE, MAX_DATE);

            // then
            assertThatIOException().isThrownBy(() -> fileParser.readData())
                .withMessage("src\\test\\resources\\empty_directory");
        }
    }

    @Nested
    @DisplayName("processLogFile method")
    class ProcessLogFile {

        @Test
        @DisplayName("Processing an invalid log file")
        void processInvalidLogFile() {
            // given
            fileParser = new FileParser("src/test/resources/logfiles/invalid.log", storage, MIN_DATE, MAX_DATE);

            // then
            assertThatIOException().isThrownBy(() -> fileParser.readData());
        }
    }
}

