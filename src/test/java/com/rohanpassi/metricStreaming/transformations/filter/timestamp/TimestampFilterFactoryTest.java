package com.rohanpassi.metricStreaming.transformations.filter.timestamp;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rohanpassi.metricStreaming.config.filter.FilterType;

public class TimestampFilterFactoryTest {

    private TimestampFilterFactory filterFactory;
    private Instant testTimestamp;

    @BeforeEach
    public void setUp() {
        filterFactory = new TimestampFilterFactory();
        testTimestamp = Instant.parse("2023-01-01T00:00:00Z");
    }

    @Test
    public void testGetFilter_GreaterThan_ReturnsCorrectFilterType() {
        // Act
        TimestampFilter filter = filterFactory.getFilter(FilterType.GT, testTimestamp);

        // Assert
        assertTrue(filter instanceof TimestampGreaterThanFilter);
    }

    @Test
    public void testGetFilter_GreaterThanEquals_ReturnsCorrectFilterType() {
        // Act
        TimestampFilter filter = filterFactory.getFilter(FilterType.GTE, testTimestamp);

        // Assert
        assertTrue(filter instanceof TimestampGreaterThanEqualsFilter);
    }

    @Test
    public void testGetFilter_LessThan_ReturnsCorrectFilterType() {
        // Act
        TimestampFilter filter = filterFactory.getFilter(FilterType.LT, testTimestamp);

        // Assert
        assertTrue(filter instanceof TimestampLessThanFilter);
    }

    @Test
    public void testGetFilter_LessThanEquals_ReturnsCorrectFilterType() {
        // Act
        TimestampFilter filter = filterFactory.getFilter(FilterType.LTE, testTimestamp);

        // Assert
        assertTrue(filter instanceof TimestampLessThanEqualsFilter);
    }

    @Test
    public void testGetFilter_Equals_ReturnsCorrectFilterType() {
        // Act
        TimestampFilter filter = filterFactory.getFilter(FilterType.EQ, testTimestamp);

        // Assert
        assertTrue(filter instanceof TimestampEqualsFilter);
    }

    @Test
    public void testGetFilter_NotEquals_ReturnsCorrectFilterType() {
        // Act
        TimestampFilter filter = filterFactory.getFilter(FilterType.NEQ, testTimestamp);

        // Assert
        assertTrue(filter instanceof TimestampNotEqualsFilter);
    }

    @Test
    public void testGetFilter_NullTimestamp_CreatesFilterWithNullTimestamp() {
        // Act - Just verify this doesn't throw an exception
        TimestampFilter filter = filterFactory.getFilter(FilterType.EQ, null);

        // Assert
        assertTrue(filter instanceof TimestampEqualsFilter);
    }
}