package edu.hw9.Task1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatsCollector {
    private static final Logger LOGGER = LogManager.getLogger();

    public final ExecutorService executor = Executors.newFixedThreadPool(5);
    private final Map<String, List<Double>> data = new HashMap<>();

    public void push(String metricName, double[] values) {
        synchronized (data) {
            data.computeIfAbsent(metricName, k -> new ArrayList<>()).addAll(toList(values));
        }
    }

    public List<StatsResult> stats() throws InterruptedException, ExecutionException {
        List<StatsResult> results = new ArrayList<>();
        List<Future<StatsResult>> futures = new ArrayList<>();

        synchronized (data) {
            for (Map.Entry<String, List<Double>> entry : data.entrySet()) {
                String metricName = entry.getKey();
                List<Double> values = entry.getValue();

                Future<StatsResult> future = executor.submit(() -> calculateStats(metricName, values));
                futures.add(future);
            }
        }

        for (Future<StatsResult> future : futures) {
            try {
                results.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.error("Error getting stats result", e);
            }
        }

        return results;
    }

    private StatsResult calculateStats(String metricName, List<Double> values) {
        double sum = 0.0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (double value : values) {
            sum += value;
            min = Math.min(min, value);
            max = Math.max(max, value);
        }

        double average = sum / values.size();

        return new StatsResult(metricName, sum, average, min, max);
    }

    private List<Double> toList(double[] values) {
        List<Double> list = new ArrayList<>();
        for (double value : values) {
            list.add(value);
        }
        return list;
    }

    public void shutdown() {
        executor.shutdown();
    }
}
