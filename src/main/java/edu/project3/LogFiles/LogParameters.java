package edu.project3.LogFiles;

import edu.project3.Formats.DataSourceFormat;
import edu.project3.Formats.LogReportFormat;
import edu.project3.Interfaces.Printer;
import edu.project3.Parsers.DataParser;
import edu.project3.Parsers.FileParser;
import edu.project3.Parsers.URLParser;
import edu.project3.Printers.ADOCPrinter;
import edu.project3.Printers.MDPrinter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;

public class LogParameters {

    private final String logPath;
    private final DataSourceFormat dataSourceFormat;
    private final LocalDate logStartDate;
    private final LocalDate logEndDate;
    private final LogReportFormat logReportFormat;
    private final LogAnalyzerStorage logStorage;

    public LogParameters(String path, String startDate, String endDate, String format) {
        this.logStartDate = DataParser.parseDateStringToDate(startDate);
        this.logEndDate = DataParser.parseDateStringToDate(endDate);
        this.dataSourceFormat = DataParser.determinePathType(path);
        this.logPath = path;
        this.logReportFormat = DataParser.determineLogReportFormat(format);
        this.logStorage = new LogAnalyzerStorage();
    }

    public void parseLogData() throws IOException, URISyntaxException {
        switch (dataSourceFormat) {
            case FILE -> new FileParser(logPath, logStorage, logStartDate, logEndDate).readData();
            case URL -> new URLParser(logPath, logStorage, logStartDate, logEndDate).readData();
            default -> {

            }
        }
    }

    public void printLogReport() {
        Printer logPrinter = (logReportFormat == null || logReportFormat == LogReportFormat.MD)
            ? new MDPrinter(logStorage, logStartDate, logEndDate)
            : new ADOCPrinter(logStorage, logStartDate, logEndDate);

        logPrinter.print();
    }
}

