package com.rohanpassi.metricStreaming.config.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = true)
public class TimestampFilterConfig extends FilterConfig {
    private Instant timestamp;

    public TimestampFilterConfig(FilterType filterType, Instant timestamp) {
        super(filterType, FilterTarget.TIMESTAMP);
        this.timestamp = timestamp;
    }
}