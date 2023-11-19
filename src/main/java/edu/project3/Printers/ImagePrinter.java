package edu.project3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImagePrinter {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void printImage() {
        String image =
            """
                \t\t\t\t\t     pN▒g▒p▒g▒▒g▒ge
                \t\t\t\t\t  _0▒▓▒▓▒▓▓▒▒▒▒▒▒▒!
                \t\t\t\t\t  4▒▒▒▒▒▓▓▓▒▓▓▒▒▒▒▒Y
                \t\t\t\t\t  |` ~~#00▓▓00▒MMMM▒|
                \t\t\t\t\t |          `gM▓M7              |
                \t\t\t\t\t |             00Q0               |
                \t\t\t\t\t #▒____g▒0▓▓P______0
                \t\t\t\t\t #▒0g_p#▓▓04▒▒&,__M#
                \t\t\t\t\t 0▒▒▒▒▒00      ]0▒▒▒▒00
                \t\t\t\t\t     |j▒▒0'      `0▒▒▒4M'
                \t\t\t\t\t     |#▒▒&▒▒gg▒▒0&  |
                \t\t\t\t\t        ▒▒00▒▒▒▒00▒▒

                """;

        printColoredImage(image);
    }

    private static void printColoredImage(String image) {

        String ANSI_RESET = "\u001B[0m";
        String ANSI_BRIGHT_GREEN = "\u001B[92m";

        StringBuilder spacedImage = new StringBuilder();

        for (char c : image.toCharArray()) {
            if (c != '\n' && c != ' ' && c != '|') {
                spacedImage.append(c).append(c);
            } else {
                spacedImage.append(c);
            }
        }

        String[] lines = spacedImage.toString().split("\n");

        for (String line : lines) {
            LOGGER.info(ANSI_BRIGHT_GREEN + line + ANSI_RESET);
        }
    }
}
