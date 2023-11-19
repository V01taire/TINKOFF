package edu.project3.Parsers;

import edu.project3.Formats.DataSourceFormat;
import edu.project3.Formats.LogReportFormat;
import java.time.LocalDate;
import java.util.regex.Pattern;

@SuppressWarnings("HideUtilityClassConstructor")
public class DataParser {

    public static LocalDate parseDateStringToDate(String dateString) {
        return dateString != null ? LocalDate.parse(dateString) : null;
    }

    public static DataSourceFormat determinePathType(String path) {
        Pattern urlPattern = Pattern.compile("^https?://");
        return urlPattern.matcher(path).find() ? DataSourceFormat.URL : DataSourceFormat.FILE;
    }

    public static LogReportFormat determineLogReportFormat(String format) {
        return switch (format) {
            case "markdown" -> LogReportFormat.MD;
            case "adoc" -> LogReportFormat.ADOC;
            default -> null;
        };
    }
}

