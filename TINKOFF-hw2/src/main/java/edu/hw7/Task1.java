package edu.hw7.Task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    public static final int THREAD_COUNT = 5;
    private static final int INCREMENT_COUNT = 100000;

    public static AtomicInteger counter = new AtomicInteger(0);

    static class IncrementThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < INCREMENT_COUNT; i++) {
                counter.incrementAndGet();
            }
        }
    }
}

