package edu.hw7;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Task1Test {

    @Test
    void testCounterIncrement() throws InterruptedException {
        Task1.counter.set(0);

        Thread[] threads = IntStream.range(0, Task1.THREAD_COUNT)
            .mapToObj(i -> new Task1.IncrementThread())
            .toArray(Thread[]::new);

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(Task1.counter.get(), Task1.THREAD_COUNT * Task1.INCREMENT_COUNT);
    }

    @Test
    void testThreadSafety() throws InterruptedException {
        Task1.counter.set(0);

        int totalIncrements = Task1.THREAD_COUNT * Task1.INCREMENT_COUNT;
        CountDownLatch latch = new CountDownLatch(Task1.THREAD_COUNT);

        for (int i = 0; i < Task1.THREAD_COUNT; i++) {
            new Thread(() -> {
                for (int j = 0; j < Task1.INCREMENT_COUNT; j++) {
                    Task1.counter.incrementAndGet();
                }
                latch.countDown();
            }).start();
        }

        latch.await();
        assertEquals(Task1.counter.get(), totalIncrements);
    }

    @Test
    void testSingleThreadIncrement() throws InterruptedException {
        Task1.counter.set(0);

        Task1.IncrementThread incrementThread = new Task1.IncrementThread();
        incrementThread.start();
        incrementThread.join();

        assertEquals(Task1.counter.get(), Task1.INCREMENT_COUNT);
    }
}


