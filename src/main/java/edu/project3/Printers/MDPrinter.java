package edu.project3.Printers;

import edu.project3.Interfaces.Printer;
import edu.project3.LogFiles.LogAnalyzerStorage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"MultipleStringLiterals", "LineLength", "MagicNumber"})
public class MDPrinter implements Printer {

    private final static int COUNT_REQUEST = 100;
    private final LogAnalyzerStorage storage;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final static Logger LOGGER = LogManager.getLogger();
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[92m";
    private static final String ANSI_RED = "\u001B[38;5;196m";
    private static final String ANSI_WHITE = "\u001B[0m";


    public MDPrinter(LogAnalyzerStorage storage, LocalDate startDate, LocalDate endDate) {
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

        printFileName();
        printDateInfo();
        printRequestInfo();

        LOGGER.info(ANSI_GREEN + "|:---------------------:|:----------------------------------------------------------------------:|");
    }

    private void printFileName() {
        LOGGER.info(ANSI_GREEN + "|"
            + ANSI_WHITE + " Directory(ies)       "
            + ANSI_GREEN + " | "
            + ANSI_WHITE + storage.getFiles().get(0)
            + ANSI_GREEN + " |");
        for (int i = 1; i < storage.getFiles().size(); i++) {
            LOGGER.info(ANSI_GREEN + "|"
                + ANSI_WHITE + "|                       | "
                + storage.getFiles().get(i) + " |");
        }
    }

    private void printDateInfo() {
        LOGGER.info(ANSI_GREEN + "|"
            + ANSI_WHITE + " Start Date           "
            + ANSI_GREEN + " | "
            + ANSI_WHITE + formatDate(startDate)
            + ANSI_GREEN + " \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t |");
        LOGGER.info(ANSI_GREEN + "|"
            + ANSI_WHITE + " End Date             "
            + ANSI_GREEN + " | "
            + ANSI_WHITE + formatDate(endDate)
            + ANSI_GREEN + " \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t |");
    }

    private void printRequestInfo() {
        LOGGER.info(ANSI_GREEN + "|"
            + ANSI_WHITE + " Request Count        "
            + ANSI_GREEN + " | "
            + ANSI_WHITE + storage.getRequestCount()
            + ANSI_GREEN + "  \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t |");

        long size = storage.getRequestCount() == 0 ? 0 : storage.getRequestSize() / storage.getRequestCount();
        LOGGER.info(ANSI_GREEN + "|"
            + ANSI_WHITE + " Average Response Size "
            + ANSI_GREEN + "| "
            + ANSI_WHITE + size + "b"
            + ANSI_GREEN + "  \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t |");
    }

    private String formatDate(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : "-";
    }

    private void printResources() {
        LOGGER.info(ANSI_RED + "☠ Requested Resources" + ANSI_RESET);
        printTableHeader(ANSI_GREEN + "|       Resource        |                                Quantity                                |");
        for (var resource : storage.getResourceCountsSorted()) {
            LOGGER.info(ANSI_GREEN + "| "
                + ANSI_WHITE + padString(resource.getKey())
                + ANSI_GREEN + "  | "
                + ANSI_WHITE + padString(Integer.toString(resource.getValue()))
                + ANSI_GREEN + "\t\t\t\t\t\t\t\t\t\t\t\t\t |");
        }
        LOGGER.info(ANSI_GREEN + "|:---------------------:|:----------------------------------------------------------------------:|");
    }

    private void printAnswerCodes() {
        LOGGER.info(ANSI_RED + "☠ Response Codes" + ANSI_RESET);
        printTableHeader(ANSI_GREEN + "|         Code          |                Name                |             Quantity              |");
        for (var code : storage.getAnswerCodeCountsSorted()) {
            LOGGER.info(ANSI_GREEN + "| "
                + ANSI_WHITE + padString(code.getKey())
                + ANSI_GREEN + "  |"
                + ANSI_WHITE + padString(getAnswerByCode(code.getKey()))
                + ANSI_GREEN + "                | "
                + ANSI_WHITE + padString(Integer.toString(code.getValue()))
                + ANSI_GREEN + "\t\t\t\t |");
        }
        LOGGER.info(ANSI_GREEN + "|:---------------------:|:----------------------------------------------------------------------:|");
    }

    private void printIP() {
        LOGGER.info(ANSI_RED + "☠ Requests from IP Addresses" + ANSI_RESET);
        printTableHeader(ANSI_GREEN + "|          IP           |                                Quantity                                |");
        for (var ip : storage.getAddressCountsSorted()) {
            if (ip.getValue() <= MDPrinter.COUNT_REQUEST) {
                break;
            }
            LOGGER.info(ANSI_GREEN + "| "
                + ANSI_WHITE + padString(ip.getKey())
                + ANSI_GREEN + "  | "
                + ANSI_WHITE + padString(Integer.toString(ip.getValue()))
                + ANSI_GREEN + "\t\t\t\t\t\t\t\t\t\t\t\t\t |");
        }
        LOGGER.info(ANSI_GREEN + "|:---------------------:|:----------------------------------------------------------------------:|");
    }

    private void printDates() {
        LOGGER.info(ANSI_RED + "☠ Requests by Dates" + ANSI_RESET);
        printTableHeader(ANSI_GREEN + "|         Date          |                                 Quantity                               |");
        for (var data : storage.getDateCountsSorted()) {
            LOGGER.info(ANSI_GREEN + "| "
                + ANSI_WHITE + padString(String.valueOf(data.getKey()))
                + ANSI_GREEN + "  | "
                + ANSI_WHITE + padString(Integer.toString(data.getValue()))
                + ANSI_GREEN + "\t\t\t\t\t\t\t\t\t\t\t\t\t |");
        }
        LOGGER.info(ANSI_GREEN + "|:---------------------:|:----------------------------------------------------------------------:|");
    }

    private void printTableHeader(String header) {
        LOGGER.info(ANSI_GREEN + "|:---------------------:|:----------------------------------------------------------------------:|");
        LOGGER.info(header);
        LOGGER.info(ANSI_GREEN + "|:---------------------:|:----------------------------------------------------------------------:|");
    }

    private String getAnswerByCode(String code) {
        return switch (code) {
            case "200" -> "Ok";
            case "206" -> "Partial Content";
            case "304" -> "Not Modified";
            case "403" -> "Forbidden";
            case "404" -> "Not Found";
            case "500" -> "Internal Server Error";
            default -> "Unknown Error";
        };
    }

    private String padString(String input) {
        return String.format("%-" + 20 + "s", input);
    }
}

