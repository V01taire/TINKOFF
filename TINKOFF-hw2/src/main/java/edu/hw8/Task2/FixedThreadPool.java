package edu.hw8.Task2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private final int numThreads;
    private final BlockingQueue<Runnable> taskQueue;
    private final Thread[] threads;

    public FixedThreadPool(int numThreads) {
        this.numThreads = numThreads;
        this.taskQueue = new ArrayBlockingQueue<>(numThreads * 2);
        this.threads = new Thread[numThreads];
    }

    public static ThreadPool create(int numThreads) {
        return new FixedThreadPool(numThreads);
    }

    @Override
    public void start() {
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                try {
                    while (true) {
                        Runnable task = taskQueue.take();
                        task.run();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        for (Thread thread : threads) {
            if (thread != null) {
                thread.interrupt();
            }
        }
    }
}

