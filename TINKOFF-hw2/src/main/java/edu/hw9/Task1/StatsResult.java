package edu.hw9.Task1;

public record StatsResult(String metricName, double sum, double average, double min, double max) {

    @Override
    public String toString() {
        return String.format("Metric: %s, Sum: %.2f, Average: %.2f, Min: %.2f, Max: %.2f",
            metricName, sum, average, min, max
        );
    }
}

