package edu.hw9.Task1Test;

import edu.hw9.Task1.StatsResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatsResultTest {

    @Test
    public void testGetMetricName() {
        StatsResult result = new StatsResult("metric1", 10.0, 5.0, 2.0, 8.0);
        assertEquals("metric1", result.metricName());
    }

    @Test
    public void testGetSum() {
        StatsResult result = new StatsResult("metric1", 10.0, 5.0, 2.0, 8.0);
        assertEquals(10.0, result.sum(), 0.001);
    }

    @Test
    public void testGetAverage() {
        StatsResult result = new StatsResult("metric1", 10.0, 5.0, 2.0, 8.0);
        assertEquals(5.0, result.average(), 0.001);
    }

    @Test
    public void testGetMin() {
        StatsResult result = new StatsResult("metric1", 10.0, 5.0, 2.0, 8.0);
        assertEquals(2.0, result.min(), 0.001);
    }

    @Test
    public void testGetMax() {
        StatsResult result = new StatsResult("metric1", 10.0, 5.0, 2.0, 8.0);
        assertEquals(8.0, result.max(), 0.001);
    }

    @Test
    public void testToString() {
        StatsResult result = new StatsResult("metric1", 10.0, 5.0, 2.0, 8.0);
        String expected = "Metric: metric1, Sum: 10,00, Average: 5,00, Min: 2,00, Max: 8,00";
        assertEquals(expected, result.toString());
    }
}
