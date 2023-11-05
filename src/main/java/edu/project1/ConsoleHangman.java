package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private static final int MAX_ATTEMPTS = 5;
    private static final Logger LOGGER = LogManager.getLogger(ConsoleHangman.class);
    private final Dictionary dictionary;
    private static final int CONSOLE_WIDTH = 120;
    private static final String PRINT_PURPLE = "\u001B[38;2;255;0;255m";
    private static final String PRINT_CYAN = "\u001B[38;2;0;255;255m";
    private static final String RESET_COLOUR = "\u001B[0m"; //
    private static final String STARS = "*  *  *  *  *  *  *  *  *  *  *  *  *  *";
    private static final String[] GRAFFITI_HANGMAN = {
        " __    __                                                                 ",
        "/  |  /  |                                                                ",
        "$$ |  $$ |  ______   _______    ______   _____  ____    ______   _______  ",
        "$$ |__$$ | /      \\ /       \\  /      \\ /     \\/    \\  /      \\ /       \\ ",
        "$$    $$ | $$$$$$  |$$$$$$$  |/$$$$$$  |$$$$$$ $$$$  | $$$$$$  |$$$$$$$  |",
        "$$$$$$$$ | /    $$ |$$ |  $$ |$$ |  $$ |$$ | $$ | $$ | /    $$ |$$ |  $$ |",
        "$$ |  $$ |/$$$$$$$ |$$ |  $$ |$$ \\__$$ |$$ | $$ | $$ |/$$$$$$$ |$$ |  $$ |",
        "$$ |  $$ |$$    $$ |$$ |  $$ |$$    $$ |$$ | $$ | $$ |$$    $$ |$$ |  $$ |",
        "$$/   $$/  $$$$$$$/ $$/   $$/  $$$$$$$ |$$/  $$/  $$/  $$$$$$$/ $$/   $$/",
        "                              /  \\__$$ |                                  ",
        "                               $$    $$ |                                   ",
        "                                $$$$$$/                                    "
    };

    public ConsoleHangman(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void run() {
        String wordToGuess = dictionary.randomWord();
        Session session = new Session(wordToGuess, MAX_ATTEMPTS);
        Scanner scanner = new Scanner(System.in);


        LOGGER.info(PRINT_CYAN + " ".repeat((CONSOLE_WIDTH - STARS.length()) / 2) + STARS + RESET_COLOUR);
        for (String line : GRAFFITI_HANGMAN) {
            int spacesToAdd = (CONSOLE_WIDTH - line.length()) / 2;
            String padding = " ".repeat(spacesToAdd);
            LOGGER.info(PRINT_PURPLE + padding + line + RESET_COLOUR);
        }

        while (true) {
            LOGGER.info("Guess a letter (or type 'exit' to quit):");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                LOGGER.info("Exiting the game. Goodbye!");
                return;
            }

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                LOGGER.info("Invalid input. Please enter a single letter.");
                continue;
            }

            char guess = input.charAt(0);
            GuessResult result = session.guess(guess);

            LOGGER.info(result.message());
            LOGGER.info("The word: " + new String(result.state()));
            LOGGER.info("");

            if (result instanceof GuessResult.Win || result instanceof GuessResult.Defeat) {
                break;
            }
        }
    }

    @SuppressWarnings("uncommentedmain")
    public static void main(String[] args) {
        ConsoleHangman hangman = new ConsoleHangman(new ConsoleDictionary());
        hangman.run();
    }
}
