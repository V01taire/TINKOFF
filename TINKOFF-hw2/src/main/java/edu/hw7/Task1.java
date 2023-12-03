package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task1 {
    public static final int THREAD_COUNT = 5;
    public static final int INCREMENT_COUNT = 100000;
    public static AtomicInteger counter = new AtomicInteger(0);

    static class IncrementThread extends Thread {
        @Override
        public void run() {
            IntStream.range(0, INCREMENT_COUNT).forEach(i -> counter.incrementAndGet());
        }
    }
}

