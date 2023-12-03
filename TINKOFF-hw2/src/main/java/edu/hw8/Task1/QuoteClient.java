package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"HideUtilityClassConstructor", "UncommentedMain"})
public class QuoteClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 5555;
    private static final int KILOBYTE_IN_BYTES = 1024;
    private static final String EXCEPTION_MESSAGE = "Exception occurred: ";
    private static final Logger LOGGER = LogManager.getLogger(QuoteClient.class);

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT)) {
            LOGGER.info("Connected to the server.");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                LOGGER.info("Ivan: ");
                String userInput = scanner.nextLine();

                try (InputStream inputStream = socket.getInputStream();
                     OutputStream outputStream = socket.getOutputStream()) {

                    outputStream.write(userInput.getBytes());

                    byte[] buffer = new byte[KILOBYTE_IN_BYTES];
                    int bytesRead = inputStream.read(buffer);
                    if (bytesRead != -1) {
                        String response = new String(buffer, 0, bytesRead);
                        LOGGER.info("Server: " + response);
                    }

                } catch (IOException e) {
                    LOGGER.error(EXCEPTION_MESSAGE, e);
                }
            }

        } catch (IOException e) {
            LOGGER.error(EXCEPTION_MESSAGE, e);
        }
    }
}


