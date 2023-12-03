package edu.hw8.Task1Test;

import edu.hw8.Task1.QuoteServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuoteServerTest {
    private static ExecutorService executorService;
    private static Thread serverThread;

    @BeforeAll
    static void setUp() {
        executorService = Executors.newSingleThreadExecutor();
        serverThread = new Thread(() -> {
            QuoteServer.main(null);
        });
        serverThread.start();
    }

    @AfterAll
    static void tearDown() {
        executorService.shutdownNow();
        serverThread.interrupt();
    }

    @Test
    void testQuoteServerWithValidRequest() throws IOException {
        Socket socket = new Socket("localhost", 5555);

        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {

            String request = "личности";
            outputStream.write(request.getBytes());

            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String response = new String(buffer, 0, bytesRead);

            assertEquals("Не переходи на личности там, где их нет", response);
        }

        socket.close();
    }
    @Test
    void testQuoteServerWithInvalidRequest() throws IOException {
        Socket socket = new Socket("localhost", 5555);

        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {

            String request = "случайноеСлово";
            outputStream.write(request.getBytes());

            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String response = new String(buffer, 0, bytesRead);

            assertEquals("Я не понимаю, о чем ты говоришь, как же ты отвратителен!", response);
        }

        socket.close();
    }

    @Test
    void testQuoteServerWithUpperCaseRequest() throws IOException {
        Socket socket = new Socket("localhost", 5555);

        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {

            String request = "ОСКОРБЛЕНИЯ";
            outputStream.write(request.getBytes());

            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String response = new String(buffer, 0, bytesRead);

            assertEquals("Если твои противники перешли на личные оскорбления, будь уверен — твоя победа не за горами", response);
        }

        socket.close();
    }
}

