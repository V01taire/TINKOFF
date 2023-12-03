package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"HideUtilityClassConstructor", "UncommentedMain", "LineLength"})
public class QuoteServer {
    private static final int PORT = 5555;
    private static final int MAX_CONNECTIONS = 5;
    private static final int KILOBYTE_IN_BYTES = 1024;
    private static final String EXCEPTION_MESSAGE = "Exception occurred: ";
    private static final Logger LOGGER = LogManager.getLogger(QuoteServer.class);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            LOGGER.info("Server started. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.submit(() -> handleClient(clientSocket));
            }
        } catch (IOException e) {
            LOGGER.error(EXCEPTION_MESSAGE, e);
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {

            byte[] buffer = new byte[KILOBYTE_IN_BYTES];
            int bytesRead = inputStream.read(buffer);
            if (bytesRead != -1) {
                String request = new String(buffer, 0, bytesRead);
                String response = getResponse(request);
                outputStream.write(response.getBytes());
            }

        } catch (IOException e) {
            LOGGER.error(EXCEPTION_MESSAGE, e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                LOGGER.error(EXCEPTION_MESSAGE, e);
            }
        }
    }

    private static String getResponse(String request) {
        return switch (request.toLowerCase()) {
            case "личности" -> "Не переходи на личности там, где их нет";
            case "оскорбления" -> "Если твои противники перешли на личные оскорбления, будь уверен — твоя победа не за горами";
            case "глупый" -> "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.";
            case "интеллект" -> "Чем ниже интеллект, тем громче оскорбления";
            default -> "Я не понимаю, о чем ты говоришь, как же ты отвратителен!";
        };
    }
}

