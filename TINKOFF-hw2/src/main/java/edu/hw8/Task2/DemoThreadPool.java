package edu.hw8.Task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"HideUtilityClassConstructor", "UncommentedMain"})
public class DemoThreadPool {
    private static final int THREE_THREADS = 3;
    private static final int MAX_FIBONACCI = 10;
    private static final Logger LOGGER = LogManager.getLogger(DemoThreadPool.class);

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(THREE_THREADS);

        List<Callable<FibonacciResult>> tasks = new ArrayList<>();
        for (int i = 0; i < MAX_FIBONACCI; i++) {
            final int n = i;
            tasks.add(() -> new FibonacciResult(n, calculateFibonacci(n)));
        }

        try {
            List<Future<FibonacciResult>> results = executorService.invokeAll(tasks);

            for (Future<FibonacciResult> result : results) {
                FibonacciResult fibonacciResult = result.get();
                LOGGER.info("Fibonacci(" + fibonacciResult.n + ") = " + fibonacciResult.result);
            }

        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("Exception occurred: ", e);
        }

        executorService.shutdown();
    }

    public static long calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
        }
    }

    public static class FibonacciResult {
        public int n;
        public long result;

        public FibonacciResult(int n, long result) {
            this.n = n;
            this.result = result;
        }
    }
}


