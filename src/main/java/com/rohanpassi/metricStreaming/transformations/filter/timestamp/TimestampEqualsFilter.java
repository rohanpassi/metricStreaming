package com.rohanpassi.metricStreaming.transformations.filter.timestamp;

import com.rohanpassi.metricStreaming.config.TimestampFilterConfig;
import com.rohanpassi.metricStreaming.dto.Metric;
import com.rohanpassi.metricStreaming.transformations.filter.MetricFilter;

import lombok.AllArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
public class TimestampEqualsFilter implements MetricFilter {
    private final Instant timestamp;

    @Override
    public boolean apply(Metric metric) {
        return metric.getTimestamp().equals(timestamp);
    }

    public TimestampEqualsFilter(TimestampFilterConfig config) {
        this.timestamp = config.getTimestamp();
    }
}