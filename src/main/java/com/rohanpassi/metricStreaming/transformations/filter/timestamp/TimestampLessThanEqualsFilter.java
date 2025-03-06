package com.rohanpassi.metricStreaming.transformations.filter.timestamp;

import java.time.Instant;

public class TimestampLessThanEqualsFilter extends TimestampFilter {

    public TimestampLessThanEqualsFilter(Instant timestamp) {
        super(timestamp);
    }

    @Override
    protected boolean compare(Instant metricTimestamp) {
        return metricTimestamp.isBefore(timestamp)|| metricTimestamp.equals(timestamp);
    }
}