package edu.hw9.Task1Test;

import edu.hw9.Task1.StatsCollector;
import edu.hw9.Task1.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatsCollectorTest {
    private StatsCollector collector;

    @BeforeEach
    public void setUp() {
        collector = new StatsCollector();
    }

    @AfterEach
    public void tearDown() {
        collector.shutdown();
    }

    @Test
    public void testPushAndStats() throws InterruptedException, ExecutionException {
        collector.push("metric1", new double[]{1.0, 2.0, 3.0});
        collector.push("metric2", new double[]{4.0, 5.0, 6.0});

        List<StatsResult> results = collector.stats();

        assertEquals(2, results.size());

        StatsResult result1 = results.get(0);
        assertEquals("metric1", result1.metricName());
        assertEquals(6.0, result1.sum(), 0.001);
        assertEquals(2.0, result1.average(), 0.001);
        assertEquals(1.0, result1.min(), 0.001);
        assertEquals(3.0, result1.max(), 0.001);

        StatsResult result2 = results.get(1);
        assertEquals("metric2", result2.metricName());
        assertEquals(15.0, result2.sum(), 0.001);
        assertEquals(5.0, result2.average(), 0.001);
        assertEquals(4.0, result2.min(), 0.001);
        assertEquals(6.0, result2.max(), 0.001);
    }

    @Test
    public void testEmptyStats() throws InterruptedException, ExecutionException {
        List<StatsResult> results = collector.stats();
        assertTrue(results.isEmpty());
    }

    @Test
    public void testShutdown() {
        collector.shutdown();
        // Ensure that the executor is shutdown
        assertTrue(collector.executor.isShutdown());
    }
}

