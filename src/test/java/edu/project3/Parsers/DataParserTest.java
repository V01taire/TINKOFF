package edu.project3.Parsers;

import static org.assertj.core.api.Assertions.assertThat;
import edu.project3.Formats.DataSourceFormat;
import edu.project3.Formats.LogReportFormat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class DataParserTest {

    @Nested
    @DisplayName("parseDateStringToDate method")
    class ParseDateStringToDate {

        @Test
        @DisplayName("Parsing a valid date string")
        void parseValidDateString() {
            // given
            String dateString = "2023-01-01";

            // when
            LocalDate result = DataParser.parseDateStringToDate(dateString);

            // then
            assertThat(result).isEqualTo(LocalDate.of(2023, 1, 1));
        }

        @Test
        @DisplayName("Parsing a null date string")
        void parseNullDateString() {
            // when
            LocalDate result = DataParser.parseDateStringToDate(null);

            // then
            assertThat(result).isNull();
        }
    }

    @Nested
    @DisplayName("determinePathType method")
    class DeterminePathType {

        @Test
        @DisplayName("Determining path type for a URL")
        void determinePathTypeForURL() {
            // given
            String path = "https://example.com";

            // when
            DataSourceFormat result = DataParser.determinePathType(path);

            // then
            assertThat(result).isEqualTo(DataSourceFormat.URL);
        }

        @Test
        @DisplayName("Determining path type for a file")
        void determinePathTypeForFile() {
            // given
            String path = "/path/to/file.txt";

            // when
            DataSourceFormat result = DataParser.determinePathType(path);

            // then
            assertThat(result).isEqualTo(DataSourceFormat.FILE);
        }
    }

    @Nested
    @DisplayName("determineLogReportFormat method")
    class DetermineLogReportFormat {

        @Test
        @DisplayName("Determining log report format for markdown")
        void determineLogReportFormatForMarkdown() {
            // given
            String format = "markdown";

            // when
            LogReportFormat result = DataParser.determineLogReportFormat(format);

            // then
            assertThat(result).isEqualTo(LogReportFormat.MD);
        }

        @Test
        @DisplayName("Determining log report format for adoc")
        void determineLogReportFormatForAdoc() {
            // given
            String format = "adoc";

            // when
            LogReportFormat result = DataParser.determineLogReportFormat(format);

            // then
            assertThat(result).isEqualTo(LogReportFormat.ADOC);
        }

        @Test
        @DisplayName("Determining log report format for unknown format")
        void determineLogReportFormatForUnknownFormat() {
            // given
            String format = "unknown";

            // when
            LogReportFormat result = DataParser.determineLogReportFormat(format);

            // then
            assertThat(result).isNull();
        }
    }
}
