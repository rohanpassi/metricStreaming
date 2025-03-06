package com.rohanpassi.metricStreaming.transformations.filter.timestamp;

import java.time.Instant;

public class TimestampEqualsFilter extends TimestampFilter {
    
    public TimestampEqualsFilter(Instant timestamp) {
        super(timestamp);
    }

    @Override
    protected boolean compare(Instant metricTimestamp) {
        return metricTimestamp.equals(timestamp);
    }
}