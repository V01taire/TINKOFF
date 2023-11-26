package edu.hw7.Task4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("HideUtilityClassConstructor")
public class MonteCarloPiMultiThreaded {

    private static final Object LOCK = new Object();
    private static final AtomicInteger CIRCLE_COUNT = new AtomicInteger(0);
    private static final int PI_APPROXIMATION_FACTOR = 4;

    public static double calculatePiMultiThreaded(long iterations, int numThreads) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CountDownLatch countDownLatch = new CountDownLatch(numThreads);

        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                int localCircleCount = 0;

                for (long j = 0; j < iterations; j++) {
                    double x = ThreadLocalRandom.current().nextDouble();
                    double y = ThreadLocalRandom.current().nextDouble();

                    double distance = Math.sqrt(x * x + y * y);

                    if (distance <= 1) {
                        localCircleCount++;
                    }
                }

                synchronized (LOCK) {
                    CIRCLE_COUNT.addAndGet(localCircleCount);
                }

                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();

        double totalCount = iterations * numThreads;
        return PI_APPROXIMATION_FACTOR * CIRCLE_COUNT.get() / totalCount;
    }
}

