package edu.hw7.Task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutionException;

public class MonteCarloPiComparison {

    private static final Logger logger = LogManager.getLogger(MonteCarloPiComparison.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[] threadCounts = {1, 20};
        long[] iterationsArray = {10_000_000L, 100_000_000L, 1_000_000_000L};

        for (long iterations : iterationsArray) {
            logger.info("Simulations: {}", iterations);
            logger.info("=====================================");
            for (int numThreads : threadCounts) {
                long startTime = System.currentTimeMillis();
                double piSingleThreaded = MonteCarloPi.calculatePi((int) iterations);
                long singleThreadedTime = System.currentTimeMillis() - startTime;

                startTime = System.currentTimeMillis();
                double piMultiThreaded = MonteCarloPiMultiThreaded.calculatePiMultiThreaded(iterations, numThreads);
                long multiThreadedTime = System.currentTimeMillis() - startTime;

                logger.info("Threads: {}", numThreads);
                logger.info("Pi Single-Threaded: {}", piSingleThreaded);
                logger.info("Pi Multi-Threaded: {}", piMultiThreaded);
                logger.info("Single-Threaded Time (ms): {}", singleThreadedTime);
                logger.info("Multi-Threaded Time (ms): {}", multiThreadedTime);
                logger.info("Acceleration: {}", (double) singleThreadedTime / multiThreadedTime);
                logger.info("=====================================");
            }
        }
    }
}
