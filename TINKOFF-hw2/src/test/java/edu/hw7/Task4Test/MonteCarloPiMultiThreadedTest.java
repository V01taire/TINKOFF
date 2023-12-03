package edu.hw7.Task4Test;

import edu.hw7.Task4.MonteCarloPiMultiThreaded;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MonteCarloPiMultiThreadedTest {

    @Test
    void testCalculatePiMultiThreadedWithValidInputs() throws InterruptedException, ExecutionException {
        long iterations = 1_000_000;
        int numThreads = 4;

        double pi = MonteCarloPiMultiThreaded.calculatePiMultiThreaded(iterations, numThreads);

        assertEquals(3.141592653589793, pi, 0.001, "Unexpected value of Pi");
    }

    @Test
    void testCalculatePiMultiThreadedWithZeroThreads() {
        long iterations = 1_000_000;
        int numThreads = 0;

        assertThrows(IllegalArgumentException.class,
            () -> MonteCarloPiMultiThreaded.calculatePiMultiThreaded(iterations, numThreads),
            "Should throw IllegalArgumentException for zero threads");
    }

    @Test
    void testCalculatePiMultiThreadedWithNegativeThreads() {
        long iterations = 1_000_000;
        int numThreads = -4;

        assertThrows(IllegalArgumentException.class,
            () -> MonteCarloPiMultiThreaded.calculatePiMultiThreaded(iterations, numThreads),
            "Should throw IllegalArgumentException for negative threads");
    }
}
