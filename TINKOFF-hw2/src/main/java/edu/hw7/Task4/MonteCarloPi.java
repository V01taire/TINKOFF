package edu.hw7.Task4;

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("HideUtilityClassConstructor")
public class MonteCarloPi {
    private static final int PI_APPROXIMATION_FACTOR = 4;

    public static double calculatePi(int iterations) {
        int circleCount = 0;
        int totalCount = 0;

        for (int i = 0; i < iterations; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();

            double distance = Math.sqrt(x * x + y * y);

            if (distance <= 1) {
                circleCount++;
            }

            totalCount++;
        }

        return PI_APPROXIMATION_FACTOR * (double) circleCount / totalCount;
    }
}

