package com.rohanpassi.metricStreaming.transformations.aggregator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AggregatorFactoryTest {

    private AggregatorFactory aggregatorFactory;
    private Map<String, String> aggregatorConfig;

    @BeforeEach
    public void setUp() {
        aggregatorFactory = new AggregatorFactory();
        aggregatorConfig = new HashMap<>();
    }

    @Test
    public void testGetSumAggregator() {
        // Arrange
        aggregatorConfig.put("aggregatorType", "SUM");

        // Act
        MetricAggregator aggregator = aggregatorFactory.getAggregator(aggregatorConfig);

        // Assert
        assertTrue(aggregator instanceof SumAggregator);
    }

    @Test
    public void testGetAverageAggregator() {
        // Arrange
        aggregatorConfig.put("aggregatorType", "AVG");

        // Act
        MetricAggregator aggregator = aggregatorFactory.getAggregator(aggregatorConfig);

        // Assert
        assertTrue(aggregator instanceof AverageAggregator);
    }

    @Test
    public void testGetMaxAggregator() {
        // Arrange
        aggregatorConfig.put("aggregatorType", "MAX");

        // Act
        MetricAggregator aggregator = aggregatorFactory.getAggregator(aggregatorConfig);

        // Assert
        assertTrue(aggregator instanceof MaxAggregator);
    }

    @Test
    public void testGetMinAggregator() {
        // Arrange
        aggregatorConfig.put("aggregatorType", "MIN");

        // Act
        MetricAggregator aggregator = aggregatorFactory.getAggregator(aggregatorConfig);

        // Assert
        assertTrue(aggregator instanceof MinAggregator);
    }

    @Test
    public void testGetAggregator_WithInvalidType_ThrowsIllegalArgumentException() {
        // Arrange
        aggregatorConfig.put("aggregatorType", "INVALID_TYPE");

        // Act & Assert
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> aggregatorFactory.getAggregator(aggregatorConfig)
        );

        // The exception should contain the invalid enum constant name
        assertTrue(exception.getMessage().contains("INVALID_TYPE"));
    }

    @Test
    public void testGetAggregator_WithMissingType_ThrowsNullPointerException() {
        // Act & Assert
        assertThrows(
                NullPointerException.class,
                () -> aggregatorFactory.getAggregator(aggregatorConfig)
        );
    }
}