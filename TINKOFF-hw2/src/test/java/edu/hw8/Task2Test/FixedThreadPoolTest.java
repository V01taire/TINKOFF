package edu.hw8.Task2Test;

import edu.hw8.Task2.FixedThreadPool;
import edu.hw8.Task2.ThreadPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FixedThreadPoolTest {
    private ThreadPool threadPool;

    @BeforeEach
    void setUp() {
        threadPool = FixedThreadPool.create(3);
    }

    @AfterEach
    void tearDown() {
        assertDoesNotThrow(() -> threadPool.close()); // Закрываем пул потоков без ошибок
    }

    @Test
    void testThreadPoolCreation() {
        assertDoesNotThrow(() -> FixedThreadPool.create(1));
        assertDoesNotThrow(() -> FixedThreadPool.create(5));
        assertThrows(IllegalArgumentException.class, () -> FixedThreadPool.create(0));
        assertThrows(IllegalArgumentException.class, () -> FixedThreadPool.create(-1));
    }

    @Test
    void testThreadPoolStart() {
        assertDoesNotThrow(() -> threadPool.start());
    }

    @Test
    void testClose() {
        assertDoesNotThrow(() -> threadPool.close());
    }

    @Test
    void testDoubleClose() {
        assertDoesNotThrow(() -> {
            threadPool.close();
            threadPool.close(); // Повторное закрытие не должно вызывать ошибок
        });
    }
}

