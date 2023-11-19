package edu.project3.Printers;

import edu.project3.Interfaces.Printer;
import edu.project3.LogFiles.LogAnalyzerStorage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"MultipleStringLiterals", "LineLength", "MagicNumber"})
public class ADOCPrinter implements Printer {

    private static final int COUNT_REQUEST = 100;
    private final LogAnalyzerStorage storage;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[92m";
    private static final String ANSI_RED = "\u001B[38;5;196m";
    private static final String ANSI_WHITE = "\u001B[0m";

    public ADOCPrinter(LogAnalyzerStorage storage, LocalDate startDate, LocalDate endDate) {
        this.storage = storage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void print() {
        ImagePrinter.printImage();
        printHeader();
        printResources();
        printDates();
        printIP();
        printAnswerCodes();
    }

    private void printHeader() {
        LOGGER.info(ANSI_RED + "☠ General Information" + ANSI_RESET);
        printTableHeader(ANSI_GREEN + "|        Metric         |                                 Value                                  |" + ANSI_RESET);

        printRow(ANSI_GREEN + "|"
            + ANSI_WHITE + " File(s)              "
            + ANSI_GREEN + " | "
            + ANSI_WHITE + storage.getFiles().get(0)
            + ANSI_GREEN + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t |");
        for (int i = 1; i < storage.getFiles().size(); i++) {
            printRow(ANSI_GREEN + "|"
                + ANSI_WHITE + "|                       | " + storage.getFiles().get(i)
                + ANSI_GREEN + " |");
        }

        printRow(ANSI_GREEN + "|"
            + ANSI_WHITE + " Start Date           "
            + ANSI_GREEN + " | "
            + ANSI_WHITE + formatDate(startDate)
            + ANSI_GREEN + " \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t |");
        printRow(ANSI_GREEN + "|"
            + ANSI_WHITE + " End Date             "
            + ANSI_GREEN + " | "
            + ANSI_WHITE + formatDate(endDate)
            + ANSI_GREEN + " \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t |");

        printRow(ANSI_GREEN + "|"
            + ANSI_WHITE + " Request Count        "
            + ANSI_GREEN + " | "
            + ANSI_WHITE + storage.getRequestCount()
            + ANSI_GREEN + "  \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t |");

        long size = storage.getRequestCount() == 0 ? 0 : storage.getRequestSize() / storage.getRequestCount();
        printRow(ANSI_GREEN + "|"
            + ANSI_WHITE + " Average Response Size "
            + ANSI_GREEN + "| "
            + ANSI_WHITE + size + "b"
            + ANSI_GREEN + "  \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t |");

        printTableFooter();
    }

    private void printResources() {
        LOGGER.info(ANSI_RED + "☠ Requested Resources" + ANSI_RESET);
        printTableHeader(ANSI_GREEN + "|       Resource        |                                Quantity                                |" + ANSI_RESET);
        for (var resource : storage.getResourceCountsSorted()) {
            printRow(ANSI_GREEN + "| "
                + ANSI_WHITE + padString(resource.getKey())
                + ANSI_GREEN + "  | "
                + ANSI_WHITE + padString(Integer.toString(resource.getValue()))
                + ANSI_GREEN + "\t\t\t\t\t\t\t\t\t\t\t\t\t |");
        }
        printTableFooter();
    }

    private void printAnswerCodes() {
        LOGGER.info(ANSI_RED + "☠ Response Codes" + ANSI_RESET);
        printTableHeader(ANSI_GREEN + "|         Code          |                Name                |             Quantity              |" + ANSI_RESET);
        for (var code : storage.getAnswerCodeCountsSorted()) {
            printRow(ANSI_GREEN + "| "
                + ANSI_WHITE + padString(code.getKey())
                + ANSI_GREEN + "  |"
                + ANSI_WHITE + padString(getAnswerByCode(code.getKey()))
                + ANSI_GREEN + "                | "
                + ANSI_WHITE + padString(Integer.toString(code.getValue()))
                + ANSI_GREEN + "\t\t\t\t |");
        }
        printTableFooter();
    }

    private void printIP() {
        LOGGER.info(ANSI_RED + "☠ Requests from IP Addresses" + ANSI_RESET);
        printTableHeader(ANSI_GREEN + "|          IP           |                                Quantity                                |" + ANSI_RESET);
        for (var ip : storage.getAddressCountsSorted()) {
            if (ip.getValue() <= COUNT_REQUEST) {
                break;
            }
            printRow(ANSI_GREEN + "| "
                + ANSI_WHITE + padString(ip.getKey())
                + ANSI_GREEN + "  | "
                + ANSI_WHITE + padString(Integer.toString(ip.getValue()))
                + ANSI_GREEN + "\t\t\t\t\t\t\t\t\t\t\t\t\t |");
        }
        printTableFooter();
    }

    private void printDates() {
        LOGGER.info(ANSI_RED + "☠ Requests by Dates" + ANSI_RESET);
        printTableHeader(ANSI_GREEN + "|         Date          |                                 Quantity                               |" + ANSI_RESET);
        for (var data : storage.getDateCountsSorted()) {
            printRow(ANSI_GREEN + "| "
                + ANSI_WHITE + padString(String.valueOf(data.getKey()))
                + ANSI_GREEN + "  | "
                + ANSI_WHITE + padString(Integer.toString(data.getValue()))
                + ANSI_GREEN + "\t\t\t\t\t\t\t\t\t\t\t\t\t |");
        }
        printTableFooter();
    }

    private void printTableHeader(String header) {
        LOGGER.info(ANSI_GREEN + "|:---------------------:|:----------------------------------------------------------------------:|" + ANSI_RESET);
        LOGGER.info(header);
        LOGGER.info(ANSI_GREEN + "|:---------------------:|:----------------------------------------------------------------------:|" + ANSI_RESET);
    }

    private void printTableFooter() {
        LOGGER.info(ANSI_GREEN + "|:---------------------:|:----------------------------------------------------------------------:|" + ANSI_RESET);
    }

    private void printRow(String row) {
        LOGGER.info(row);
    }

    private String formatDate(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : "-";
    }

    private String getAnswerByCode(String code) {
        return switch (code) {
            case "200" -> "Ok";
            case "206" -> "Partial Content";
            case "304" -> "Not Modified";
            case "403" -> "Forbidden";
            case "404" -> "Not Found";
            case "416" -> "Range Not Satisfiable";
            case "500" -> "Internal Server Error";
            default -> "Unknown Error";
        };
    }

    private String padString(String input) {
        return String.format("%-" + 20 + "s", input);
    }
}
