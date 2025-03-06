package com.rohanpassi.metricStreaming.transformations.aggregator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rohanpassi.metricStreaming.dto.Metric;

public class AverageAggregatorTest {

    private AverageAggregator averageAggregator;

    @BeforeEach
    public void setUp() {
        averageAggregator = new AverageAggregator();
    }

    @Test
    public void testAggregate_WithSingleMetric_ReturnsSameValue() {
        // Arrange
        Metric metric = new Metric(10.0, Instant.ofEpochSecond(1000L));
        List<Metric> metrics = List.of(metric);

        // Act
        List<Metric> result = averageAggregator.aggregate(metrics);

        // Assert
        assertEquals(1, result.size());
        assertEquals(10.0, result.get(0).getValue());
        assertEquals(Instant.ofEpochSecond(1000L), result.get(0).getTimestamp());
    }

    @Test
    public void testAggregate_WithMultipleMetrics_ReturnsAverageValue() {
        // Arrange
        List<Metric> metrics = List.of(
                new Metric(10.0, Instant.ofEpochSecond(1000L)),
                new Metric(20.0, Instant.ofEpochSecond(1001L)),
                new Metric(30.0, Instant.ofEpochSecond(1002L))
        );

        // Act
        List<Metric> result = averageAggregator.aggregate(metrics);

        // Assert
        assertEquals(1, result.size());
        assertEquals(20.0, result.get(0).getValue());
        assertEquals(Instant.ofEpochSecond(1000L), result.get(0).getTimestamp()); // Should use the timestamp of the first metric
    }

    @Test
    public void testAggregate_WithEmptyMetricsList_ReturnsEmptyList() {
        // Arrange
        List<Metric> metrics = List.of();

        // Act
        List<Metric> result = averageAggregator.aggregate(metrics);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void testAggregate_WithGroupedMetrics_ReturnsAggregatedMap() {
        // Arrange
        Map<String, List<Metric>> groupedMetrics = new HashMap<>();

        // Group 1: metrics for "cpu"
        List<Metric> cpuMetrics = List.of(
                new Metric(10.0, Instant.ofEpochSecond(1000L)),
                new Metric(20.0, Instant.ofEpochSecond(1001L))
        );
        groupedMetrics.put("cpu", cpuMetrics);

        // Group 2: metrics for "memory"
        List<Metric> memoryMetrics = List.of(
                new Metric(30.0, Instant.ofEpochSecond(1000L)),
                new Metric(50.0, Instant.ofEpochSecond(1001L)),
                new Metric(40.0, Instant.ofEpochSecond(1002L))
        );
        groupedMetrics.put("memory", memoryMetrics);

        // Group 3: empty group
        groupedMetrics.put("empty", List.of());

        // Act
        Map<String, List<Metric>> result = averageAggregator.aggregate(groupedMetrics);

        // Assert
        assertEquals(3, result.size());

        // Check cpu group
        List<Metric> aggregatedCpu = result.get("cpu");
        assertEquals(1, aggregatedCpu.size());
        assertEquals(15.0, aggregatedCpu.get(0).getValue());
        assertEquals(Instant.ofEpochSecond(1000L), aggregatedCpu.get(0).getTimestamp());

        // Check memory group
        List<Metric> aggregatedMemory = result.get("memory");
        assertEquals(1, aggregatedMemory.size());
        assertEquals(40.0, aggregatedMemory.get(0).getValue());
        assertEquals(Instant.ofEpochSecond(1000L), aggregatedMemory.get(0).getTimestamp());

        // Check empty group
        List<Metric> aggregatedEmpty = result.get("empty");
        assertTrue(aggregatedEmpty.isEmpty());
    }

    @Test
    public void testAggregate_WithZeroValueMetrics_ReturnsZeroAverage() {
        // Arrange
        List<Metric> metrics = List.of(
                new Metric(0.0, Instant.ofEpochSecond(1000L)),
                new Metric(0.0, Instant.ofEpochSecond(1001L)),
                new Metric(0.0, Instant.ofEpochSecond(1002L))
        );

        // Act
        List<Metric> result = averageAggregator.aggregate(metrics);

        // Assert
        assertEquals(1, result.size());
        assertEquals(0.0, result.get(0).getValue());
        assertEquals(Instant.ofEpochSecond(1000L), result.get(0).getTimestamp());
    }

    @Test
    public void testAggregate_WithNegativeValueMetrics_ReturnsCorrectAverage() {
        // Arrange
        List<Metric> metrics = List.of(
                new Metric(-10.0, Instant.ofEpochSecond(1000L)),
                new Metric(10.0, Instant.ofEpochSecond(1001L))
        );

        // Act
        List<Metric> result = averageAggregator.aggregate(metrics);

        // Assert
        assertEquals(1, result.size());
        assertEquals(0.0, result.get(0).getValue());
        assertEquals(Instant.ofEpochSecond(1000L), result.get(0).getTimestamp());
    }
}