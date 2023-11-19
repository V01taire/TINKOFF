package edu.project3.LogFiles;

import java.io.IOException;
import java.net.URISyntaxException;

@SuppressWarnings({"HideUtilityClassConstructor", "InnerAssignment"})
public class Main {

    private static String path = "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
    private static String dateFrom = "2015-05-18";
    private static String dateTo = "2015-06-18";
    private static String format = "markdown";

    public static void main(String[] args) throws RuntimeException {

        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "--path" -> path = args[i + 1];
                case "--from" -> dateFrom = args[i + 1];
                case "--to" -> dateTo = args[i + 1];
                case "--format" -> format = args[i + 1];
                default -> throw new IllegalArgumentException("Unknown flag: " + args[i]);
            }
        }

        if (path == null) {
            throw new IllegalArgumentException("Need path");
        }

        LogParameters logStats = new LogParameters(path, dateFrom, dateTo, format);

        try {
            logStats.parseLogData();
            logStats.printLogReport();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Cannot parse by path", e);
        }
    }
}
