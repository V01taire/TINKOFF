package edu.hw7.Task4Test;

import edu.hw7.Task4.MonteCarloPi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonteCarloPiTest {

    @Test
    void testCalculatePi() {
        int[] iterationsArray = {1_000, 10_000, 100_000, 1_000_000};

        for (int iterations : iterationsArray) {
            double pi = MonteCarloPi.calculatePi(iterations);

            assertTrue(pi >= 3.0 && pi <= 4.0, "Pi value is out of expected range for " + iterations + " iterations: " + pi);
        }
    }

    @Test
    void testCalculatePiWithZeroIterations() {
        int iterations = 0;
        double pi = MonteCarloPi.calculatePi(iterations);

        assertEquals(0.0, pi, "Pi value should be 0 for 0 iterations");
    }

    @Test
    void testCalculatePiWithNegativeIterations() {
        int iterations = -100;

        assertThrows(IllegalArgumentException.class, () -> MonteCarloPi.calculatePi(iterations),
            "Should throw IllegalArgumentException for negative iterations");
    }
}

