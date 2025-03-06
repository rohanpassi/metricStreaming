package com.rohanpassi.metricStreaming.transformations.filter.timestamp;

import java.time.Instant;

public class TimestampNotEqualsFilter extends TimestampFilter {

    public TimestampNotEqualsFilter(Instant timestamp) {
        super(timestamp);
    }

    @Override
    protected boolean compare(Instant metricTimestamp) {
        return metricTimestamp.isAfter(timestamp) || metricTimestamp.isBefore(timestamp);
    }
}