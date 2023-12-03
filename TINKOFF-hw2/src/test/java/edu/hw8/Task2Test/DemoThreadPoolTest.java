package edu.hw8.Task2Test;

import edu.hw8.Task2.DemoThreadPool;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class DemoThreadPoolTest {

    @Test
    void testCalculateFibonacci() {
        assertEquals(0, DemoThreadPool.calculateFibonacci(0));
        assertEquals(1, DemoThreadPool.calculateFibonacci(1));
        assertEquals(1, DemoThreadPool.calculateFibonacci(2));
        assertEquals(2, DemoThreadPool.calculateFibonacci(3));
        assertEquals(3, DemoThreadPool.calculateFibonacci(4));
        assertEquals(5, DemoThreadPool.calculateFibonacci(5));
    }

    @Test
    void testFibonacciResult() {
        DemoThreadPool.FibonacciResult result = new DemoThreadPool.FibonacciResult(3, 5);
        assertEquals(3, result.n);
        assertEquals(5, result.result);
    }

    @Test
    void testDemoThreadPool() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Callable<DemoThreadPool.FibonacciResult>> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int n = i;
            tasks.add(() -> new DemoThreadPool.FibonacciResult(n, DemoThreadPool.calculateFibonacci(n)));
        }

        List<Future<DemoThreadPool.FibonacciResult>> results = executorService.invokeAll(tasks);

        for (Future<DemoThreadPool.FibonacciResult> result : results) {
            DemoThreadPool.FibonacciResult fibonacciResult = result.get();
            assertEquals(fibonacciResult.result, DemoThreadPool.calculateFibonacci(fibonacciResult.n));
        }

        executorService.shutdown();
    }
}

