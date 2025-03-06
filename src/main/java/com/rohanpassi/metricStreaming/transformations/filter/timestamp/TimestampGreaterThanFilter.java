package com.rohanpassi.metricStreaming.transformations.filter.timestamp;

import java.time.Instant;

public class TimestampGreaterThanFilter extends TimestampFilter {

    public TimestampGreaterThanFilter(Instant timestamp) {
        super(timestamp);
    }

    @Override
    protected boolean compare(Instant metricTimestamp) {
        return metricTimestamp.isAfter(timestamp);
    }
}