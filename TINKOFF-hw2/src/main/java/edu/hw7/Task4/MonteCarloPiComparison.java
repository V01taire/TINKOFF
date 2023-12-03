package edu.hw7.Task4;

import java.util.concurrent.ExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"HideUtilityClassConstructor", "UncommentedMain"})
public class MonteCarloPiComparison {

    private static final Logger LOGGER_SINGLE_THREADED = LogManager.getLogger(MonteCarloPi.class);
    private static final Logger LOGGER_MULTI_THREADED = LogManager.getLogger(MonteCarloPiMultiThreaded.class);
    private static final int MAX_THREADS = 20;
    private static final long TEN_MILLION_ITERATIONS = 10_000_000L;
    private static final long ONE_HUNDRED_MILLION_ITERATIONS = 100_000_000L;
    private static final long ONE_BILLION_ITERATIONS = 1_000_000_000L;
    private static final String BOARDER = "+-----------------------------------------------+";

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[] threadCounts = {1, MAX_THREADS};
        long[] iterationsArray = {TEN_MILLION_ITERATIONS, ONE_HUNDRED_MILLION_ITERATIONS, ONE_BILLION_ITERATIONS};

        for (long iterations : iterationsArray) {
            LOGGER_SINGLE_THREADED.info("Simulations: {}", iterations);
            LOGGER_SINGLE_THREADED.info(BOARDER);
            long startTime = System.currentTimeMillis();
            double piSingleThreaded = MonteCarloPi.calculatePi((int) iterations);
            long singleThreadedTime = System.currentTimeMillis() - startTime;
            LOGGER_SINGLE_THREADED.info("Pi Single-Threaded: {}", piSingleThreaded);
            LOGGER_SINGLE_THREADED.info("Single-Threaded Time (ms): {}", singleThreadedTime);
            LOGGER_SINGLE_THREADED.info("Deviation: 0.0");
            LOGGER_SINGLE_THREADED.info(BOARDER);

            for (int numThreads : threadCounts) {
                if (numThreads == 1) {
                    continue;
                }

                LOGGER_MULTI_THREADED.info("Threads: {}", numThreads);
                startTime = System.currentTimeMillis();
                double piMultiThreaded = MonteCarloPiMultiThreaded.calculatePiMultiThreaded(iterations, numThreads);
                long multiThreadedTime = System.currentTimeMillis() - startTime;
                LOGGER_MULTI_THREADED.info("Pi Multi-Threaded: {}", piMultiThreaded);
                LOGGER_MULTI_THREADED.info("Multi-Threaded Time (ms): {}", multiThreadedTime);
                LOGGER_MULTI_THREADED.info("Acceleration: {}", (double) singleThreadedTime / multiThreadedTime);
                double deviation = Math.abs(piMultiThreaded - piSingleThreaded);
                LOGGER_MULTI_THREADED.info("Deviation: {}", deviation);
                LOGGER_MULTI_THREADED.info(BOARDER);
            }
        }
    }
}



