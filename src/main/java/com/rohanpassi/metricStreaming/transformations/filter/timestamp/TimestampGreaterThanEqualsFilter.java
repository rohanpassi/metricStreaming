package com.rohanpassi.metricStreaming.transformations.filter.timestamp;

import java.time.Instant;

public class TimestampGreaterThanEqualsFilter extends TimestampFilter {

    public TimestampGreaterThanEqualsFilter(Instant timestamp) {
        super(timestamp);
    }

    @Override
    protected boolean compare(Instant metricTimestamp) {
        return metricTimestamp.isAfter(timestamp)|| metricTimestamp.equals(timestamp);
    }
}