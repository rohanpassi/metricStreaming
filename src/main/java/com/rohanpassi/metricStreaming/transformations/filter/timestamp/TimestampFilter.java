package com.rohanpassi.metricStreaming.transformations.filter.timestamp;

import java.time.Instant;

import com.rohanpassi.metricStreaming.dto.Metric;
import com.rohanpassi.metricStreaming.transformations.filter.MetricFilter;

public abstract class TimestampFilter implements MetricFilter {
    protected final Instant timestamp;

    public TimestampFilter(Instant timestamp) {
        this.timestamp = timestamp;
    }

    protected abstract boolean compare(Instant metricTimestamp);

    @Override
    public boolean apply(Metric metric){
        return compare(metric.getTimestamp());
    }
}

