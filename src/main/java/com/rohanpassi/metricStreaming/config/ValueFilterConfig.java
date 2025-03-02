package com.rohanpassi.metricStreaming.config;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ValueFilterConfig extends FilterConfig {
    private int threshold;

    public ValueFilterConfig(FilterType filterType, int threshold) {
        super(filterType, FilterTarget.VALUE);
        this.threshold = threshold;
    }
}